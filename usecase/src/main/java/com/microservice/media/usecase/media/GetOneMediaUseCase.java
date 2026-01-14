package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class GetOneMediaUseCase {
    private final MediaRepository mediaRepository;
    private final ProviderRepository providerRepository;

    public Media handle(UUID uuid) {
        Media media = mediaRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));

        if (media.getProviderUuid() != null) {
            UUID providerUuid = UUID.fromString(media.getProviderUuid());
            providerRepository.findOneByUuid(providerUuid)
                    .ifPresent(media::setProvider);
        }

        return media;
    }
}
