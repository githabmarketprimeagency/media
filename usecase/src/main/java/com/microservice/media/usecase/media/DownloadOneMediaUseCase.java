package com.microservice.media.usecase.media;

import lombok.AllArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.microservice.media.domain.exception.NotFoundException;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.ProviderRepository;

@AllArgsConstructor
public class DownloadOneMediaUseCase {
    private final MediaRepository mediaRepository;
    private final ProviderRepository providerRepository;

    public Resource handle(UUID uuid) {
        Media media = mediaRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(uuid));

        if (media.getProviderUuid() == null) {
            throw new NotFoundException("Provider not found for media " + uuid);
        }

        UUID providerUuid = UUID.fromString(media.getProviderUuid());
        Provider provider = providerRepository.findOneByUuid(providerUuid)
                .orElseThrow(() -> new NotFoundException("Provider", providerUuid));

        Path filePath = Paths.get(media.getProviderPath(), media.getName());
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new NotFoundException("File not found: " + filePath);
        }

        return new FileSystemResource(file);
    }
}
