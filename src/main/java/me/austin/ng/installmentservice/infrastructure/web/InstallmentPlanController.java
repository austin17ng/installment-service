package me.austin.ng.installmentservice.infrastructure.web;

import me.austin.ng.installmentservice.domain.usecase.CreateInstallmentUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentPlansByTransactionUseCase;
import me.austin.ng.installmentservice.infrastructure.web.dto.CreateInstallmentRequest;
import me.austin.ng.installmentservice.infrastructure.web.dto.CreateInstallmentResponse;
import me.austin.ng.installmentservice.infrastructure.web.dto.InstallmentPlanResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstallmentPlanController {

    private final GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransaction;
    private final CreateInstallmentUseCase createInstallment;

    public InstallmentPlanController(GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransaction,
                                      CreateInstallmentUseCase createInstallment) {
        this.getInstallmentPlansByTransaction = getInstallmentPlansByTransaction;
        this.createInstallment = createInstallment;
    }

    @GetMapping("/transactions/{transactionId}/installments")
    public List<InstallmentPlanResponse> getInstallmentPlansByTransaction(
            @PathVariable String transactionId) {
        return getInstallmentPlansByTransaction.execute(transactionId).stream()
                .map(InstallmentPlanResponse::from)
                .toList();
    }

    @PostMapping("/installments")
    public CreateInstallmentResponse createInstallment(@RequestBody CreateInstallmentRequest request) {
        return CreateInstallmentResponse.from(
                createInstallment.execute(request.transactionId(), request.term())
        );
    }
}
