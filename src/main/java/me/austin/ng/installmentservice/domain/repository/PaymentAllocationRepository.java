package me.austin.ng.installmentservice.domain.repository;

import me.austin.ng.installmentservice.domain.model.PaymentAllocation;

import java.util.List;
import java.util.Optional;

public interface PaymentAllocationRepository {
    PaymentAllocation save(PaymentAllocation allocation);
    Optional<PaymentAllocation> findById(String id);
    List<PaymentAllocation> findByScheduleId(String scheduleId);
}
