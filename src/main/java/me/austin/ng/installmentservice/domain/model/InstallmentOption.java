package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;

public class InstallmentOption {
    private final InstallmentTerm term;
    private final BigDecimal monthlyPrincipal;
    private final BigDecimal monthlyInterestRate;
    private final BigDecimal processingFee;
    private final BigDecimal totalAmount;

    public InstallmentOption(InstallmentTerm term, BigDecimal monthlyPrincipal,
                             BigDecimal monthlyInterestRate, BigDecimal processingFee,
                             BigDecimal totalAmount) {
        this.term = term;
        this.monthlyPrincipal = monthlyPrincipal;
        this.monthlyInterestRate = monthlyInterestRate;
        this.processingFee = processingFee;
        this.totalAmount = totalAmount;
    }

    public InstallmentTerm getTerm() {
        return term;
    }

    public BigDecimal getMonthlyPrincipal() {
        return monthlyPrincipal;
    }

    public BigDecimal getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
