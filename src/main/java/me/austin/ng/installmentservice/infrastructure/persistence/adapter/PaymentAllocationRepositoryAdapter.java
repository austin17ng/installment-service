package me.austin.ng.installmentservice.infrastructure.persistence.adapter;

import me.austin.ng.installmentservice.domain.model.PaymentAllocation;
import me.austin.ng.installmentservice.domain.repository.PaymentAllocationRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.jpa.JpaPaymentAllocationRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.mapper.PaymentAllocationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentAllocationRepositoryAdapter implements PaymentAllocationRepository {

    private final JpaPaymentAllocationRepository jpaRepo;

    public PaymentAllocationRepositoryAdapter(JpaPaymentAllocationRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public PaymentAllocation save(PaymentAllocation allocation) {
        return PaymentAllocationMapper.toDomain(
                jpaRepo.save(PaymentAllocationMapper.toEntity(allocation))
        );
    }

    @Override
    public Optional<PaymentAllocation> findById(String id) {
        return jpaRepo.findById(UUID.fromString(id))
                .map(PaymentAllocationMapper::toDomain);
    }

    @Override
    public List<PaymentAllocation> findByScheduleId(String scheduleId) {
        return jpaRepo.findByScheduleId(UUID.fromString(scheduleId)).stream()
                .map(PaymentAllocationMapper::toDomain)
                .toList();
    }
}
