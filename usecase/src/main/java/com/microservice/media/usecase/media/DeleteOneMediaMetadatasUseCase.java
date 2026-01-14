package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.Metadata;
import com.microservice.media.domain.repository.MediaRepository;

@AllArgsConstructor
public class DeleteOneMediaMetadatasUseCase {
    private final MediaRepository mediaRepository;

    public void handle(UUID uuid, List<Metadata> metadatasToDelete) {
        /*
        Media media = mediaRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));

        if (media.getMetadatas() == null) {
            media.setMetadatas(new ArrayList<>());
        }

        List<String> namesToDelete = metadatasToDelete.stream()
                .map(Metadata::getName)
                .collect(Collectors.toList());

        List<Metadata> updatedMetadatas = media.getMetadatas().stream()
                .filter(m -> !namesToDelete.contains(m.getName()))
                .collect(Collectors.toList());

        media.setMetadatas(updatedMetadatas);
        mediaRepository.save(media);
        */
    }
}
