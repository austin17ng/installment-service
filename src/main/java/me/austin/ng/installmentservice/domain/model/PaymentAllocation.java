package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;

public class PaymentAllocation {
    private final String id;
    private final String scheduleId;
    private final String repaymentId;
    private final BigDecimal amount;

    public PaymentAllocation(String id, String scheduleId, String repaymentId, BigDecimal amount) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.repaymentId = repaymentId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getRepaymentId() {
        return repaymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
