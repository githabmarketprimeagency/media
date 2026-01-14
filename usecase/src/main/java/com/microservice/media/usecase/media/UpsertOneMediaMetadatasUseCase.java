package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.Metadata;
import com.microservice.media.domain.repository.MediaRepository;

@AllArgsConstructor
public class UpsertOneMediaMetadatasUseCase {
    private final MediaRepository mediaRepository;

    public void handle(UUID uuid, List<Metadata> metadatasToUpsert) {
        /*
        Media media = mediaRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));

        if (media.getMetadatas() == null) {
            media.setMetadatas(new ArrayList<>());
        }

        Map<String, Metadata> metadataMap = new HashMap<>();
        for (Metadata existingMetadata : media.getMetadatas()) {
            metadataMap.put(existingMetadata.getName(), existingMetadata);
        }

        for (Metadata newMetadata : metadatasToUpsert) {
            metadataMap.put(newMetadata.getName(), newMetadata);
        }

        media.setMetadatas(new ArrayList<>(metadataMap.values()));
        mediaRepository.save(media);
        */
    }
}
