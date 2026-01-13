package com.microservice.media.usecase.requestcheck;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.repository.RequestCheckRepository;

@AllArgsConstructor
public class GetOneRequestCheckUseCase {
    private final RequestCheckRepository requestCheckRepository;

    public RequestCheck handle(UUID uuid) {
        return requestCheckRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));
    }
}
