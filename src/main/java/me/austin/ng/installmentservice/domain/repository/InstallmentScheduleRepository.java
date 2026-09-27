package me.austin.ng.installmentservice.domain.repository;

import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;

import java.util.List;
import java.util.Optional;

public interface InstallmentScheduleRepository {
    InstallmentSchedule save(InstallmentSchedule schedule);
    Optional<InstallmentSchedule> findById(String id);
    List<InstallmentSchedule> findByPlanId(String planId);
}
