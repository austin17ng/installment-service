package me.austin.ng.installmentservice.infrastructure.persistence.jpa;

import me.austin.ng.installmentservice.infrastructure.persistence.entity.InstallmentScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaInstallmentScheduleRepository extends JpaRepository<InstallmentScheduleEntity, UUID> {
    List<InstallmentScheduleEntity> findByPlanId(UUID planId);
}
