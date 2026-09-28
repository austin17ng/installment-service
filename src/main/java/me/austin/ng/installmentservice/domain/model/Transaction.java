package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;

public class Transaction {
    private final String id;
    private final BigDecimal amount;

    public Transaction(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
