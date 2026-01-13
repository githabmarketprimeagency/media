package com.microservice.media.usecase.provider;

import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.repository.ProviderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOneProviderUseCase {
    private final ProviderRepository providerRepository;

    public Provider handle(Provider provider) {
        return providerRepository.save(provider);
    }
}
