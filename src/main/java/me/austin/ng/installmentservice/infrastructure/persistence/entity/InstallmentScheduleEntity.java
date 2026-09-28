package me.austin.ng.installmentservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import me.austin.ng.installmentservice.domain.model.InstallmentScheduleStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "installment_schedule")
public class InstallmentScheduleEntity {

    @Id
    private UUID id;

    @Column(name = "plan_id", nullable = false)
    private UUID planId;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InstallmentScheduleStatus status;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal principal;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal interest;

    @Column(name = "principal_paid", nullable = false, precision = 19, scale = 4)
    private BigDecimal principalPaid;

    @Column(name = "interest_paid", nullable = false, precision = 19, scale = 4)
    private BigDecimal interestPaid;

    protected InstallmentScheduleEntity() {
    }

    public InstallmentScheduleEntity(UUID id, UUID planId, LocalDate dueDate,
                                     InstallmentScheduleStatus status, BigDecimal principal,
                                     BigDecimal interest, BigDecimal principalPaid,
                                     BigDecimal interestPaid) {
        this.id = id;
        this.planId = planId;
        this.dueDate = dueDate;
        this.status = status;
        this.principal = principal;
        this.interest = interest;
        this.principalPaid = principalPaid;
        this.interestPaid = interestPaid;
    }

    public UUID getId() { return id; }
    public UUID getPlanId() { return planId; }
    public LocalDate getDueDate() { return dueDate; }
    public InstallmentScheduleStatus getStatus() { return status; }
    public BigDecimal getPrincipal() { return principal; }
    public BigDecimal getInterest() { return interest; }
    public BigDecimal getPrincipalPaid() { return principalPaid; }
    public BigDecimal getInterestPaid() { return interestPaid; }
}
