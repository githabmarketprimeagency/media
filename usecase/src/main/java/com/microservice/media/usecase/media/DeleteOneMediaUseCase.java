package com.microservice.media.usecase.media;

import com.microservice.media.domain.repository.MediaRepository;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteOneMediaUseCase {
    private final MediaRepository mediaRepository;

    public void handle(UUID uuid) {
        mediaRepository.deleteOneByUuid(uuid);
    }
}
