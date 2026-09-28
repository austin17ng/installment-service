package me.austin.ng.installmentservice.domain.usecase;

import me.austin.ng.installmentservice.domain.calculator.InstallmentCalculator;
import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
import me.austin.ng.installmentservice.domain.model.InstallmentOption;
import me.austin.ng.installmentservice.domain.model.InstallmentTerm;
import me.austin.ng.installmentservice.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GetInstallmentPlansByTransactionUseCase {

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
                    BigDecimal interestRate = INTEREST_RATES.get(term);
                    BigDecimal fee = PROCESSING_FEES.get(term);
                    BigDecimal totalInterest = amount.multiply(interestRate)
                            .multiply(BigDecimal.valueOf(term.getMonths()));
                    BigDecimal totalAmount = amount.add(totalInterest).add(fee);
                    return new InstallmentOption(term, monthlyPrincipal, interestRate, fee, totalAmount);
                })
                .toList();
    }
}
