package me.austin.ng.installmentservice.domain.model;

import java.math.BigDecimal;

public class Transaction {
    private final String id;
    private final String accountId;
    private final BigDecimal amount;

    public Transaction(String id, String accountId, BigDecimal amount) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
