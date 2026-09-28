package me.austin.ng.installmentservice.domain.gateway;

import me.austin.ng.installmentservice.domain.model.Transaction;

import java.util.Optional;

public interface TransactionGateway {
    Optional<Transaction> getTransaction(String transactionId);
}
