package me.austin.ng.installmentservice.infrastructure.web.dto;

import me.austin.ng.installmentservice.domain.model.InstallmentPlan;
import me.austin.ng.installmentservice.domain.model.InstallmentSchedule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record InstallmentDetailResponse(
        String id,
        String accountId,
        String transactionId,
        int termMonths,
        LocalDate startDate,
        String status,
        BigDecimal totalPrincipalAmount,
        BigDecimal monthlyInterestRate,
        BigDecimal installmentProcessingFee,
        List<ScheduleResponse> schedules
) {
    public static InstallmentDetailResponse from(InstallmentPlan plan) {
        return new InstallmentDetailResponse(
                plan.getId(),
                plan.getAccountId(),
                plan.getTxnId(),
                plan.getTerm().getMonths(),
                plan.getStartDate(),
                plan.getStatus().name(),
                plan.getTotalPrincipalAmount(),
                plan.getMonthlyInterestRate(),
                plan.getInstallmentProcessingFee(),
                plan.getSchedules().stream().map(ScheduleResponse::from).toList()
        );
    }

    public record ScheduleResponse(
            String id,
            LocalDate dueDate,
            String status,
            BigDecimal principal,
            BigDecimal interest
    ) {
        public static ScheduleResponse from(InstallmentSchedule schedule) {
            return new ScheduleResponse(
                    schedule.getId(),
                    schedule.getDueDate(),
                    schedule.getStatus().name(),
                    schedule.getPrincipal(),
                    schedule.getInterest()
            );
        }
    }
}
