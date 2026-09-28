package me.austin.ng.installmentservice.infrastructure.persistence.mapper;

import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;
import me.austin.ng.installmentservice.infrastructure.persistence.entity.InstallmentScheduleEntity;

import java.util.UUID;

public final class InstallmentScheduleMapper {

    private InstallmentScheduleMapper() {
    }

    public static InstallmentScheduleEntity toEntity(InstallmentSchedule domain) {
        return new InstallmentScheduleEntity(
                UUID.fromString(domain.getId()),
                UUID.fromString(domain.getPlanId()),
                domain.getDueDate(),
                domain.getStatus(),
                domain.getPrincipal(),
                domain.getInterest(),
                domain.getPrincipalPaid(),
                domain.getInterestPaid()
        );
    }

    public static InstallmentSchedule toDomain(InstallmentScheduleEntity entity) {
        return new InstallmentSchedule(
                entity.getId().toString(),
                entity.getPlanId().toString(),
                entity.getDueDate(),
                entity.getStatus(),
                entity.getPrincipal(),
                entity.getInterest(),
                entity.getPrincipalPaid(),
                entity.getInterestPaid()
        );
    }
}
