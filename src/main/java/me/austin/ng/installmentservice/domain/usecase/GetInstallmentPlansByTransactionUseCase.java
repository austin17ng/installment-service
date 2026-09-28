package me.austin.ng.installmentservice.domain.usecase;

import me.austin.ng.installmentservice.domain.common.InstallmentCalculator;
import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
import me.austin.ng.installmentservice.domain.model.InstallmentOption;
import me.austin.ng.installmentservice.domain.model.InstallmentTerm;
import me.austin.ng.installmentservice.domain.common.InstallmentTermPolicy;
import me.austin.ng.installmentservice.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GetInstallmentPlansByTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final InstallmentCalculator calculator;

    public GetInstallmentPlansByTransactionUseCase(TransactionGateway transactionGateway,
                                                    InstallmentCalculator calculator) {
        this.transactionGateway = transactionGateway;
        this.calculator = calculator;
    }

    public List<InstallmentOption> execute(String transactionId) {
        Transaction transaction = transactionGateway.getTransaction(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transactionId));

        BigDecimal amount = transaction.getAmount();

        return Arrays.stream(InstallmentTerm.values())
                .map(term -> {
                    BigDecimal monthlyPrincipal = calculator.calculatePrincipalPerMonth(amount, term);
                    BigDecimal interestRate = InstallmentTermPolicy.getInterestRate(term);
                    BigDecimal fee = InstallmentTermPolicy.getProcessingFee(term);
                    BigDecimal totalInterest = amount.multiply(interestRate)
                            .multiply(BigDecimal.valueOf(term.getMonths()));
                    BigDecimal totalAmount = amount.add(totalInterest).add(fee);
                    return new InstallmentOption(term, monthlyPrincipal, interestRate, fee, totalAmount);
                })
                .toList();
    }
}
