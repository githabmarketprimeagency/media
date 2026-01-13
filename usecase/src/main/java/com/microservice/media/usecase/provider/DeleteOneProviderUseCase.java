package com.microservice.media.usecase.provider;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class DeleteOneProviderUseCase {
    private final ProviderRepository providerRepository;

    public void handle(UUID uuid) {
        providerRepository.deleteOneByUuid(uuid);
    }
}
