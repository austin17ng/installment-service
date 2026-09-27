package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentSchedule {
    private final String id;
    private final String planId;
    private final LocalDate dueDate;
    private final InstallmentScheduleStatus status;

    private final BigDecimal principal;
    private final BigDecimal interest;
    private final BigDecimal principalPaid;
    private final BigDecimal interestPaid;

    public InstallmentSchedule(String id, String planId, LocalDate dueDate, InstallmentScheduleStatus status, BigDecimal principal, BigDecimal interest, BigDecimal principalPaid, BigDecimal interestPaid) {
        this.id = id;
        this.planId = planId;
        this.dueDate = dueDate;
        this.status = status;
        this.principal = principal;
        this.interest = interest;
        this.principalPaid = principalPaid;
        this.interestPaid = interestPaid;
    }

    public String getId() {
        return id;
    }

    public String getPlanId() {
        return planId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InstallmentScheduleStatus getStatus() {
        return status;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getPrincipalPaid() {
        return principalPaid;
    }

    public BigDecimal getInterestPaid() {
        return interestPaid;
    }
}
