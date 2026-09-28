package me.austin.ng.installmentservice.domain.usecase;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.repository.InstallmentPlanRepository;

import java.util.Optional;

public class GetInstallmentDetailUseCase {

    private final InstallmentPlanRepository planRepository;

    public GetInstallmentDetailUseCase(InstallmentPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public InstallmentPlan execute(String id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Installment plan not found: " + id));
    }
}
