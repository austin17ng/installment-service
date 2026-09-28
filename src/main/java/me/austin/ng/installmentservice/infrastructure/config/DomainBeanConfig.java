package me.austin.ng.installmentservice.infrastructure.config;

import me.austin.ng.installmentservice.domain.calculator.InstallmentCalculator;
import me.austin.ng.installmentservice.domain.gateway.TransactionGateway;
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
}
