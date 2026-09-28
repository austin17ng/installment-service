package me.austin.ng.installmentservice.infrastructure.persistence.mapper;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;
import me.austin.ng.installmentservice.infrastructure.persistence.entity.InstallmentPlanEntity;

import java.util.List;
import java.util.UUID;

public final class InstallmentPlanMapper {

    private InstallmentPlanMapper() {
    }

    public static InstallmentPlanEntity toEntity(InstallmentPlan domain) {
        return new InstallmentPlanEntity(
                UUID.fromString(domain.getId()),
                domain.getAccountId(),
                domain.getTxnId(),
                domain.getTerm(),
                domain.getStartDate(),
                domain.getStatus(),
                domain.getTotalPrincipalAmount(),
                domain.getMonthlyInterestRate(),
                domain.getInstallmentProcessingFee()
        );
    }

    public static InstallmentPlan toDomain(InstallmentPlanEntity entity, List<InstallmentSchedule> schedules) {
        return new InstallmentPlan(
                entity.getId().toString(),
                entity.getAccountId(),
                entity.getTxnId(),
                entity.getTerm(),
                entity.getStartDate(),
                entity.getStatus(),
                entity.getTotalPrincipalAmount(),
                entity.getMonthlyInterestRate(),
                entity.getInstallmentProcessingFee(),
                schedules
        );
    }
}
