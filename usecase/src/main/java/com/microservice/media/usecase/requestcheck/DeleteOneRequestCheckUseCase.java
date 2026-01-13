package com.microservice.media.usecase.requestcheck;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.repository.RequestCheckRepository;

@AllArgsConstructor
public class DeleteOneRequestCheckUseCase {
    private final RequestCheckRepository requestCheckRepository;

    public void handle(UUID uuid) {
        requestCheckRepository.deleteOneByUuid(uuid);
    }
}
