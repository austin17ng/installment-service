package me.austin.ng.installmentservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import me.austin.ng.installmentservice.domain.model.InstallmentPlanStatus;
import me.austin.ng.installmentservice.domain.model.InstallmentTerm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "installment_plan")
public class InstallmentPlanEntity {

    @Id
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "txn_id", nullable = false)
    private String txnId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InstallmentTerm term;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InstallmentPlanStatus status;

    @Column(name = "total_principal_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal totalPrincipalAmount;

    @Column(name = "monthly_interest_rate", nullable = false, precision = 19, scale = 6)
    private BigDecimal monthlyInterestRate;

    @Column(name = "installment_processing_fee", nullable = false, precision = 19, scale = 4)
    private BigDecimal installmentProcessingFee;

    protected InstallmentPlanEntity() {
    }

    public InstallmentPlanEntity(UUID id, String accountId, String txnId, InstallmentTerm term,
                                 LocalDate startDate, InstallmentPlanStatus status,
                                 BigDecimal totalPrincipalAmount, BigDecimal monthlyInterestRate,
                                 BigDecimal installmentProcessingFee) {
        this.id = id;
        this.accountId = accountId;
        this.txnId = txnId;
        this.term = term;
        this.startDate = startDate;
        this.status = status;
        this.totalPrincipalAmount = totalPrincipalAmount;
        this.monthlyInterestRate = monthlyInterestRate;
        this.installmentProcessingFee = installmentProcessingFee;
    }

    public UUID getId() { return id; }
    public String getAccountId() { return accountId; }
    public String getTxnId() { return txnId; }
    public InstallmentTerm getTerm() { return term; }
    public LocalDate getStartDate() { return startDate; }
    public InstallmentPlanStatus getStatus() { return status; }
    public BigDecimal getTotalPrincipalAmount() { return totalPrincipalAmount; }
    public BigDecimal getMonthlyInterestRate() { return monthlyInterestRate; }
    public BigDecimal getInstallmentProcessingFee() { return installmentProcessingFee; }
}
