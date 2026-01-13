package com.microservice.media.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.media.domain.repository.RequestCheckRepository;
import com.microservice.media.usecase.requestcheck.CountRequestChecksUseCase;
import com.microservice.media.usecase.requestcheck.CreateOneRequestCheckUseCase;
import com.microservice.media.usecase.requestcheck.DeleteOneRequestCheckUseCase;
import com.microservice.media.usecase.requestcheck.FindRequestChecksUseCase;
import com.microservice.media.usecase.requestcheck.GetOneRequestCheckUseCase;
import com.microservice.media.usecase.service.SpamDetectionService;
import com.microservice.media.usecase.service.SpamStateService;

@Configuration
public class RequestCheckConfig {

    @Bean
    CountRequestChecksUseCase getCountRequestChecksUseCase(RequestCheckRepository requestCheckRepository) {
        return new CountRequestChecksUseCase(requestCheckRepository);
    }

    @Bean
    CreateOneRequestCheckUseCase getCreateOneRequestCheckUseCase(RequestCheckRepository requestCheckRepository, SpamStateService spamStateService, SpamDetectionService spamDetectionService) {
        return new CreateOneRequestCheckUseCase(requestCheckRepository, spamStateService, spamDetectionService);
    }

    @Bean
    DeleteOneRequestCheckUseCase getDeleteOneRequestCheckUseCase(RequestCheckRepository requestCheckRepository) {
        return new DeleteOneRequestCheckUseCase(requestCheckRepository);
    }

    @Bean
    FindRequestChecksUseCase getFindRequestChecksUseCase(RequestCheckRepository requestCheckRepository) {
        return new FindRequestChecksUseCase(requestCheckRepository);
    }

    @Bean
    GetOneRequestCheckUseCase getGetOneRequestCheckUseCase(RequestCheckRepository requestCheckRepository) {
        return new GetOneRequestCheckUseCase(requestCheckRepository);
    }

}
