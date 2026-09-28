package me.austin.ng.installmentservice.infrastructure.web.dto;

import me.austin.ng.installmentservice.domain.model.InstallmentOption;

import java.math.BigDecimal;

public record InstallmentPlanResponse(
        int termMonths,
        BigDecimal monthlyPrincipal,
        BigDecimal monthlyInterestRate,
        BigDecimal processingFee,
        BigDecimal totalAmount
) {
    public static InstallmentPlanResponse from(InstallmentOption option) {
        return new InstallmentPlanResponse(
                option.getTerm().getMonths(),
                option.getMonthlyPrincipal(),
                option.getMonthlyInterestRate(),
                option.getProcessingFee(),
                option.getTotalAmount()
        );
    }
}
