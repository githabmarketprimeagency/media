package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class CreateOneMediaUseCase {
    private final MediaRepository mediaRepository;
    private final ProviderRepository providerRepository;

    public Media handle(Media media) {
        if (media.getProviderUuid() != null) {
            UUID providerUuid = UUID.fromString(media.getProviderUuid());
            providerRepository.findOneByUuid(providerUuid)
                    .orElseThrow(() -> new NotFoundException("Provider", providerUuid));
        }

        Media createdMedia = mediaRepository.save(media);

        if (createdMedia.getProviderUuid() != null) {
            UUID providerUuid = UUID.fromString(createdMedia.getProviderUuid());
            providerRepository.findOneByUuid(providerUuid)
                    .ifPresent(createdMedia::setProvider);
        }

        return createdMedia;
    }
}
