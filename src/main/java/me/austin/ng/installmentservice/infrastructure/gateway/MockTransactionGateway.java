package me.austin.ng.installmentservice.infrastructure.gateway;

import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
import me.austin.ng.installmentservice.domain.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class MockTransactionGateway implements TransactionGateway {

    @Override
    public Optional<Transaction> getTransaction(String transactionId) {
        return Optional.of(new Transaction(transactionId, "acc-001", new BigDecimal("12000000")));
    }
}
