package me.austin.ng.installmentservice.domain.usecase;

import me.austin.ng.installmentservice.domain.common.InstallmentCalculator;
import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.model.InstallmentPlanStatus;
import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;
import me.austin.ng.installmentservice.domain.model.InstallmentScheduleStatus;
import me.austin.ng.installmentservice.domain.model.InstallmentTerm;
import me.austin.ng.installmentservice.domain.common.InstallmentTermPolicy;
import me.austin.ng.installmentservice.domain.model.Transaction;
import me.austin.ng.installmentservice.domain.repository.InstallmentPlanRepository;
import me.austin.ng.installmentservice.domain.repository.InstallmentScheduleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateInstallmentUseCase {

    private final TransactionGateway transactionGateway;
    private final InstallmentCalculator calculator;
    private final InstallmentPlanRepository planRepository;
    private final InstallmentScheduleRepository scheduleRepository;

    public CreateInstallmentUseCase(TransactionGateway transactionGateway,
                                     InstallmentCalculator calculator,
                                     InstallmentPlanRepository planRepository,
                                     InstallmentScheduleRepository scheduleRepository) {
        this.transactionGateway = transactionGateway;
        this.calculator = calculator;
        this.planRepository = planRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public InstallmentPlan execute(String transactionId, int termMonths) {
        Transaction transaction = transactionGateway.getTransaction(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transactionId));

        InstallmentTerm term = InstallmentTerm.fromMonths(termMonths);
        BigDecimal amount = transaction.getAmount();
        BigDecimal interestRate = InstallmentTermPolicy.getInterestRate(term);
        BigDecimal fee = InstallmentTermPolicy.getProcessingFee(term);
        BigDecimal monthlyPrincipal = calculator.calculatePrincipalPerMonth(amount, term);

        String planId = UUID.randomUUID().toString();
        LocalDate startDate = LocalDate.now();

        List<InstallmentSchedule> schedules = buildSchedules(planId, term, startDate, monthlyPrincipal, amount, interestRate);

        InstallmentPlan plan = new InstallmentPlan(
                planId,
                transaction.getAccountId(),
                transactionId,
                term,
                startDate,
                InstallmentPlanStatus.PENDING,
                amount,
                interestRate,
                fee,
                schedules
        );

        planRepository.save(plan);
        for (InstallmentSchedule schedule : schedules) {
            scheduleRepository.save(schedule);
        }

        return plan;
    }

    private List<InstallmentSchedule> buildSchedules(String planId, InstallmentTerm term,
                                                      LocalDate startDate, BigDecimal monthlyPrincipal,
                                                      BigDecimal totalAmount, BigDecimal interestRate) {
        List<InstallmentSchedule> schedules = new ArrayList<>();
        BigDecimal principalAssigned = BigDecimal.ZERO;

        for (int i = 0; i < term.getMonths(); i++) {
            BigDecimal principal;
            if (i == term.getMonths() - 1) {
                principal = totalAmount.subtract(principalAssigned);
            } else {
                principal = monthlyPrincipal;
                principalAssigned = principalAssigned.add(monthlyPrincipal);
            }

            BigDecimal interest = totalAmount.multiply(interestRate);

            schedules.add(new InstallmentSchedule(
                    UUID.randomUUID().toString(),
                    planId,
                    startDate.plusMonths(i + 1),
                    InstallmentScheduleStatus.PENDING,
                    principal,
                    interest,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            ));
        }

        return schedules;
    }
}
