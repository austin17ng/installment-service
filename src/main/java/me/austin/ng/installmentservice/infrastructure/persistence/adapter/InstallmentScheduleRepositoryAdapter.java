package me.austin.ng.installmentservice.infrastructure.persistence.adapter;

import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;
import me.austin.ng.installmentservice.domain.repository.InstallmentScheduleRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.jpa.JpaInstallmentScheduleRepository;
import me.austin.ng.installmentservice.infrastructure.persistence.mapper.InstallmentScheduleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InstallmentScheduleRepositoryAdapter implements InstallmentScheduleRepository {

    private final JpaInstallmentScheduleRepository jpaRepo;

    public InstallmentScheduleRepositoryAdapter(JpaInstallmentScheduleRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public InstallmentSchedule save(InstallmentSchedule schedule) {
        return InstallmentScheduleMapper.toDomain(
                jpaRepo.save(InstallmentScheduleMapper.toEntity(schedule))
        );
    }

    @Override
    public Optional<InstallmentSchedule> findById(String id) {
        return jpaRepo.findById(UUID.fromString(id))
                .map(InstallmentScheduleMapper::toDomain);
    }

    @Override
    public List<InstallmentSchedule> findByPlanId(String planId) {
        return jpaRepo.findByPlanId(UUID.fromString(planId)).stream()
                .map(InstallmentScheduleMapper::toDomain)
                .toList();
    }
}
