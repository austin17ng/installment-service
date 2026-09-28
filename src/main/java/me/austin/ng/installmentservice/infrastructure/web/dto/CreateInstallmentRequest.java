package me.austin.ng.installmentservice.infrastructure.web.dto;

public record CreateInstallmentRequest(
        String transactionId,
        int term
) {
}
