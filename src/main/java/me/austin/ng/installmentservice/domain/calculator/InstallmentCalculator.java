package me.austin.ng.installmentservice.domain.calculator;

import me.austin.ng.installmentservice.domain.model.InstallmentTerm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InstallmentCalculator {
    public BigDecimal calculatePrincipalPerMonth(
            BigDecimal totalPrincipal,
            InstallmentTerm term
    ) {
        return totalPrincipal.divide(BigDecimal.valueOf(term.getMonths()), 2, RoundingMode.DOWN);
    }
}
