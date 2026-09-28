package me.austin.ng.installmentservice.domain.calculator;

import me.austin.ng.installmentservice.domain.model.InstallmentTerm;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class InstallmentCalculator {
    public BigDecimal calculatePrincipalPerMonth(
            BigDecimal totalPrincipal,
            InstallmentTerm term
    ) {
        return totalPrincipal.divide(BigDecimal.valueOf(term.getMonths()), 2, RoundingMode.DOWN);
    }
}
