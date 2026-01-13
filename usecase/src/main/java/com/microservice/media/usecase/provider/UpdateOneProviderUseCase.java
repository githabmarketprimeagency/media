package com.microservice.media.usecase.provider;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class UpdateOneProviderUseCase {
    private final ProviderRepository providerRepository;

    public Provider handle(UUID uuid, Provider provider) {
        providerRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));

        provider.setUuid(uuid);
        return providerRepository.save(provider);
    }
}
