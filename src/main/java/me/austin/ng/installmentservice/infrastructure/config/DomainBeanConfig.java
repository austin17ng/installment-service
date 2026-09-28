package me.austin.ng.installmentservice.infrastructure.config;

import me.austin.ng.installmentservice.domain.common.InstallmentCalculator;
import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
import me.austin.ng.installmentservice.domain.repository.InstallmentPlanRepository;
import me.austin.ng.installmentservice.domain.repository.InstallmentScheduleRepository;
import me.austin.ng.installmentservice.domain.usecase.CreateInstallmentUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentDetailUseCase;
import me.austin.ng.installmentservice.domain.usecase.GetInstallmentPlansByTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfig {

    @Bean
    public InstallmentCalculator installmentCalculator() {
        return new InstallmentCalculator();
    }

    @Bean
    public GetInstallmentPlansByTransactionUseCase getInstallmentPlansByTransactionUseCase(
            TransactionGateway transactionGateway,
            InstallmentCalculator calculator) {
        return new GetInstallmentPlansByTransactionUseCase(transactionGateway, calculator);
    }

    @Bean
    public CreateInstallmentUseCase createInstallmentUseCase(
            TransactionGateway transactionGateway,
            InstallmentCalculator calculator,
            InstallmentPlanRepository planRepository,
            InstallmentScheduleRepository scheduleRepository) {
        return new CreateInstallmentUseCase(transactionGateway, calculator, planRepository, scheduleRepository);
    }

    @Bean
    public GetInstallmentDetailUseCase getInstallmentDetailUseCase(
            InstallmentPlanRepository planRepository) {
        return new GetInstallmentDetailUseCase(planRepository);
    }
}
