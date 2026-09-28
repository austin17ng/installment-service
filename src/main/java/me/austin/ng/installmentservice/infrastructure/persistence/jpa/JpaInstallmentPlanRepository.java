package me.austin.ng.installmentservice.infrastructure.persistence.jpa;

import me.austin.ng.installmentservice.infrastructure.persistence.entity.InstallmentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaInstallmentPlanRepository extends JpaRepository<InstallmentPlanEntity, UUID> {
    List<InstallmentPlanEntity> findByAccountId(String accountId);
}
