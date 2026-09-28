package me.austin.ng.installmentservice.infrastructure.persistence.jpa;

import me.austin.ng.installmentservice.infrastructure.persistence.entity.PaymentAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaPaymentAllocationRepository extends JpaRepository<PaymentAllocationEntity, UUID> {
    List<PaymentAllocationEntity> findByScheduleId(UUID scheduleId);
}
