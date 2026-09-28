package me.austin.ng.installmentservice.domain.usecase;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.repository.InstallmentPlanRepository;

import java.util.List;

public class GetInstallmentsByAccountUseCase {

    private final InstallmentPlanRepository planRepository;

    public GetInstallmentsByAccountUseCase(InstallmentPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<InstallmentPlan> execute(String accountId) {
        return planRepository.findByAccountId(accountId);
    }
}
