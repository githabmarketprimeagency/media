package com.microservice.media.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.media.domain.repository.ProviderRepository;
import com.microservice.media.usecase.provider.CountProvidersUseCase;
import com.microservice.media.usecase.provider.CreateOneProviderUseCase;
import com.microservice.media.usecase.provider.DeleteOneProviderUseCase;
import com.microservice.media.usecase.provider.FindProvidersUseCase;
import com.microservice.media.usecase.provider.GetOneProviderUseCase;
import com.microservice.media.usecase.provider.UpdateOneProviderUseCase;

@Configuration
public class ProviderConfig {

    @Bean
    CountProvidersUseCase getCountProvidersUseCase(ProviderRepository providerRepository) {
        return new CountProvidersUseCase(providerRepository);
    }

    @Bean
    CreateOneProviderUseCase getCreateOneProviderUseCase(ProviderRepository providerRepository) {
        return new CreateOneProviderUseCase(providerRepository);
    }

    @Bean
    DeleteOneProviderUseCase getDeleteOneProviderUseCase(ProviderRepository providerRepository) {
        return new DeleteOneProviderUseCase(providerRepository);
    }

    @Bean
    FindProvidersUseCase getFindProvidersUseCase(ProviderRepository providerRepository) {
        return new FindProvidersUseCase(providerRepository);
    }

    @Bean
    GetOneProviderUseCase getGetOneProviderUseCase(ProviderRepository providerRepository) {
        return new GetOneProviderUseCase(providerRepository);
    }

    @Bean
    UpdateOneProviderUseCase getUpdateOneProviderUseCase(ProviderRepository providerRepository) {
        return new UpdateOneProviderUseCase(providerRepository);
    }

}
