package me.austin.ng.installmentservice.domain.common;

import me.austin.ng.installmentservice.domain.model.InstallmentTerm;

import java.math.BigDecimal;
import java.util.Map;

public final class InstallmentTermPolicy {

    private static final Map<InstallmentTerm, BigDecimal> INTEREST_RATES = Map.of(
            InstallmentTerm.THREE, new BigDecimal("0.01"),
            InstallmentTerm.SIX, new BigDecimal("0.012"),
            InstallmentTerm.NINE, new BigDecimal("0.014"),
            InstallmentTerm.TWELVE, new BigDecimal("0.015"),
            InstallmentTerm.EIGHTEEN, new BigDecimal("0.016"),
            InstallmentTerm.TWENTY_FOUR, new BigDecimal("0.017")
    );

    private static final Map<InstallmentTerm, BigDecimal> PROCESSING_FEES = Map.of(
            InstallmentTerm.THREE, new BigDecimal("100000"),
            InstallmentTerm.SIX, new BigDecimal("150000"),
            InstallmentTerm.NINE, new BigDecimal("200000"),
            InstallmentTerm.TWELVE, new BigDecimal("250000"),
            InstallmentTerm.EIGHTEEN, new BigDecimal("300000"),
            InstallmentTerm.TWENTY_FOUR, new BigDecimal("350000")
    );

    private InstallmentTermPolicy() {
    }

    public static BigDecimal getInterestRate(InstallmentTerm term) {
        return INTEREST_RATES.get(term);
    }

    public static BigDecimal getProcessingFee(InstallmentTerm term) {
        return PROCESSING_FEES.get(term);
    }
}
