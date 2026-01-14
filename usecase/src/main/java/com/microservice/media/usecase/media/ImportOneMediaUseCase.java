package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class ImportOneMediaUseCase {
    private final MediaRepository mediaRepository;
    private final ProviderRepository providerRepository;

    public Media handle(Media media) {
        if (media.getProviderUuid() != null) {
            UUID providerUuid = UUID.fromString(media.getProviderUuid());
            providerRepository.findOneByUuid(providerUuid)
                    .orElseThrow(() -> new NotFoundException("Provider", providerUuid));
        }

        Media importedMedia = mediaRepository.save(media);

        if (importedMedia.getProviderUuid() != null) {
            UUID providerUuid = UUID.fromString(importedMedia.getProviderUuid());
            providerRepository.findOneByUuid(providerUuid)
                    .ifPresent(importedMedia::setProvider);
        }

        return importedMedia;
    }
}
