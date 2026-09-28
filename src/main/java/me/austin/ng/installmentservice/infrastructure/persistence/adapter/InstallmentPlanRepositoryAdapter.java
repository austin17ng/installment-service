package me.austin.ng.installmentservice.infrastructure.persistence.adapter;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;
import me.austin.ng.installmentservice.domain.repository.InstallmentPlanRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.entity.InstallmentPlanEntity;
import me.austin.ng.installmentservice.infrastructure.persistence.jpa.JpaInstallmentPlanRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.jpa.JpaInstallmentScheduleRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.mapper.InstallmentPlanMapper;
import me.austin.ng.installmentservice.infrastructure.persistence.mapper.InstallmentScheduleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InstallmentPlanRepositoryAdapter implements InstallmentPlanRepository {

    private final JpaInstallmentPlanRepository jpaRepo;
    private final JpaInstallmentScheduleRepository jpaScheduleRepo;

    public InstallmentPlanRepositoryAdapter(JpaInstallmentPlanRepository jpaRepo,
                                            JpaInstallmentScheduleRepository jpaScheduleRepo) {
        this.jpaRepo = jpaRepo;
        this.jpaScheduleRepo = jpaScheduleRepo;
    }

    @Override
    public InstallmentPlan save(InstallmentPlan plan) {
        InstallmentPlanEntity saved = jpaRepo.save(InstallmentPlanMapper.toEntity(plan));
        return InstallmentPlanMapper.toDomain(saved, plan.getSchedules());
    }

    @Override
    public Optional<InstallmentPlan> findById(String id) {
        UUID uuid = UUID.fromString(id);
        return jpaRepo.findById(uuid)
                .map(entity -> {
                    List<InstallmentSchedule> schedules = jpaScheduleRepo.findByPlanId(uuid).stream()
                            .map(InstallmentScheduleMapper::toDomain)
                            .toList();
                    return InstallmentPlanMapper.toDomain(entity, schedules);
                });
    }

    @Override
    public List<InstallmentPlan> findByAccountId(String accountId) {
        return jpaRepo.findByAccountId(accountId).stream()
                .map(entity -> {
                    List<InstallmentSchedule> schedules = jpaScheduleRepo.findByPlanId(entity.getId()).stream()
                            .map(InstallmentScheduleMapper::toDomain)
                            .toList();
                    return InstallmentPlanMapper.toDomain(entity, schedules);
                })
                .toList();
    }
}
