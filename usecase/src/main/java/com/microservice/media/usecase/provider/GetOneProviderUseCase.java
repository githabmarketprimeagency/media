package com.microservice.media.usecase.provider;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class GetOneProviderUseCase {
    private final ProviderRepository providerRepository;

    public Provider handle(UUID uuid) {
        return providerRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));
    }
}
