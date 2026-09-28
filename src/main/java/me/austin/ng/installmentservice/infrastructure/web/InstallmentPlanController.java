package me.austin.ng.installmentservice.infrastructure.web;

import me.austin.ng.installmentservice.domain.usecase.GetInstallmentPlansByTransactionUseCase;
import me.austin.ng.installmentservice.infrastructure.web.dto.InstallmentPlanResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstallmentPlanController {

    private final GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransaction;

    public InstallmentPlanController(GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransaction) {
        this.getInstallmentPlansByTransaction = getInstallmentPlansByTransaction;
    }

    @GetMapping("/transactions/{transactionId}/installments")
    public List<InstallmentPlanResponse> getInstallmentPlansByTransaction(
            @PathVariable String transactionId) {
        return getInstallmentPlansByTransaction.execute(transactionId).stream()
                .map(InstallmentPlanResponse::from)
                .toList();
    }
}
