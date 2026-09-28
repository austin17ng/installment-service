package me.austin.ng.installmentservice.infrastructure.web;

import me.austin.ng.installmentservice.domain.usecase.CreateInstallmentUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentDetailUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentPlansByTransactionUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentsByAccountUseCase;
import me.austin.ng.installmentservice.infrastructure.web.dto.CreateInstallmentRequest;
import me.austin.ng.installmentservice.infrastructure.web.dto.InstallmentDetailResponse;
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
    private final GetInstallmentDetailUseCase getInstallmentDetail;
    private final GetInstallmentsByAccountUseCase getInstallmentsByAccount;

    public InstallmentPlanController(GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransaction,
                                      CreateInstallmentUseCase createInstallment,
                                      GetInstallmentDetailUseCase getInstallmentDetail,
                                      GetInstallmentsByAccountUseCase getInstallmentsByAccount) {
        this.getInstallmentPlansByTransaction = getInstallmentPlansByTransaction;
        this.createInstallment = createInstallment;
        this.getInstallmentDetail = getInstallmentDetail;
        this.getInstallmentsByAccount = getInstallmentsByAccount;
    }

    @GetMapping("/transactions/{transactionId}/installments")
    public List<InstallmentPlanResponse> getInstallmentPlansByTransaction(
            @PathVariable String transactionId) {
        return getInstallmentPlansByTransaction.execute(transactionId).stream()
                .map(InstallmentPlanResponse::from)
                .toList();
    }

    @PostMapping("/installments")
    public InstallmentDetailResponse createInstallment(@RequestBody CreateInstallmentRequest request) {
        return InstallmentDetailResponse.from(
                createInstallment.execute(request.transactionId(), request.term())
        );
    }

    @GetMapping("/installments/{id}")
    public InstallmentDetailResponse getInstallmentDetail(@PathVariable String id) {
        return InstallmentDetailResponse.from(getInstallmentDetail.execute(id));
    }

    @GetMapping("/accounts/{accountId}/installments")
    public List<InstallmentDetailResponse> getInstallmentsByAccount(@PathVariable String accountId) {
        return getInstallmentsByAccount.execute(accountId).stream()
                .map(InstallmentDetailResponse::from)
                .toList();
    }
}
