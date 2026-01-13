package com.microservice.media.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.media.usecase.service.SpamDetectionService;
import com.microservice.media.usecase.service.SpamStateService;

@Configuration
public class ServiceConfig {

    @Bean
    SpamDetectionService getSpamDetectionService() {
        return new SpamDetectionService();
    }

    @Bean
    SpamStateService getSpamStateService() {
        return new SpamStateService();
    }

}
