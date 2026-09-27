package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InstallmentPlan {
    private final String id;
    private final String accountId;
    private final String txnId;
    private final InstallmentTerm term;
    private final LocalDate startDate;
    private final InstallmentPlanStatus status;
    private final BigDecimal totalPrincipalAmount;
    private final BigDecimal monthlyInterestRate;
    private final BigDecimal installmentProcessingFee;
    private final List<InstallmentSchedule> schedules;

    public InstallmentPlan(
            String id,
            String accountId,
            String txnId,
            InstallmentTerm term,
            LocalDate startDate,
            InstallmentPlanStatus status,
            BigDecimal totalPrincipalAmount,
            BigDecimal monthlyInterestRate,
            BigDecimal installmentProcessingFee,
            List<InstallmentSchedule> schedules
    ) {
        this.id = id;
        this.accountId = accountId;
        this.txnId = txnId;
        this.term = term;
        this.startDate = startDate;
        this.status = status;
        this.totalPrincipalAmount = totalPrincipalAmount;
        this.monthlyInterestRate = monthlyInterestRate;
        this.installmentProcessingFee = installmentProcessingFee;
        this.schedules = schedules;
    }

    public String getId() {
        return id;
    }

    public InstallmentTerm getTerm() {
        return term;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public InstallmentPlanStatus getStatus() {
        return status;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTxnId() {
        return txnId;
    }

    public BigDecimal getTotalPrincipalAmount() {
        return totalPrincipalAmount;
    }

    public BigDecimal getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public BigDecimal getInstallmentProcessingFee() {
        return installmentProcessingFee;
    }

    public List<InstallmentSchedule> getSchedules() {
        return schedules;
    }
}
