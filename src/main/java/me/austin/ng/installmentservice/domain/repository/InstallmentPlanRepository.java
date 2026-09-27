package me.austin.ng.installmentservice.domain.repository;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;

import java.util.List;
import java.util.Optional;

public interface InstallmentPlanRepository {
    InstallmentPlan save(InstallmentPlan plan);
    Optional<InstallmentPlan> findById(String id);
    List<InstallmentPlan> findByAccountId(String accountId);
}
