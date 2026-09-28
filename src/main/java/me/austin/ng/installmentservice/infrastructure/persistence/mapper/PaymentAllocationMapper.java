package me.austin.ng.installmentservice.infrastructure.persistence.mapper;

import me.austin.ng.installmentservice.domain.model.PaymentAllocation;
import me.austin.ng.installmentservice.infrastructure.persistence.entity.PaymentAllocationEntity;

import java.util.UUID;

public final class PaymentAllocationMapper {

    private PaymentAllocationMapper() {
    }

    public static PaymentAllocationEntity toEntity(PaymentAllocation domain) {
        return new PaymentAllocationEntity(
                UUID.fromString(domain.getId()),
                UUID.fromString(domain.getScheduleId()),
                domain.getRepaymentId(),
                domain.getAmount()
        );
    }

    public static PaymentAllocation toDomain(PaymentAllocationEntity entity) {
        return new PaymentAllocation(
                entity.getId().toString(),
                entity.getScheduleId().toString(),
                entity.getRepaymentId(),
                entity.getAmount()
        );
    }
}
