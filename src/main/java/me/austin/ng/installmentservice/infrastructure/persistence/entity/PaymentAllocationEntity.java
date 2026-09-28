package me.austin.ng.installmentservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment_allocation")
public class PaymentAllocationEntity {

    @Id
    private UUID id;

    @Column(name = "schedule_id", nullable = false)
    private UUID scheduleId;

    @Column(name = "repayment_id", nullable = false)
    private String repaymentId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    protected PaymentAllocationEntity() {
    }

    public PaymentAllocationEntity(UUID id, UUID scheduleId, String repaymentId, BigDecimal amount) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.repaymentId = repaymentId;
        this.amount = amount;
    }

    public UUID getId() { return id; }
    public UUID getScheduleId() { return scheduleId; }
    public String getRepaymentId() { return repaymentId; }
    public BigDecimal getAmount() { return amount; }
}
