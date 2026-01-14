package com.microservice.media.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.ProviderRepository;
import com.microservice.media.usecase.media.CountMediasUseCase;
import com.microservice.media.usecase.media.CreateOneMediaUseCase;
import com.microservice.media.usecase.media.DeleteOneMediaUseCase;
import com.microservice.media.usecase.media.DeleteOneMediaMetadatasUseCase;
import com.microservice.media.usecase.media.DownloadOneMediaUseCase;
import com.microservice.media.usecase.media.FindMediasUseCase;
import com.microservice.media.usecase.media.GetOneMediaUseCase;
import com.microservice.media.usecase.media.ImportOneMediaUseCase;
import com.microservice.media.usecase.media.ReadOneMediaUseCase;
import com.microservice.media.usecase.media.UpdateOneMediaUseCase;
import com.microservice.media.usecase.media.UpsertOneMediaMetadatasUseCase;

@Configuration
public class MediaConfig {

    @Bean
    CountMediasUseCase getCountMediasUseCase(MediaRepository mediaRepository) {
        return new CountMediasUseCase(mediaRepository);
    }

    @Bean
    CreateOneMediaUseCase getCreateOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new CreateOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    DeleteOneMediaUseCase getDeleteOneMediaUseCase(MediaRepository mediaRepository) {
        return new DeleteOneMediaUseCase(mediaRepository);
    }

    @Bean
    DeleteOneMediaMetadatasUseCase getDeleteOneMediaMetadatasUseCase(MediaRepository mediaRepository) {
        return new DeleteOneMediaMetadatasUseCase(mediaRepository);
    }

    @Bean
    DownloadOneMediaUseCase getDownloadOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new DownloadOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    FindMediasUseCase getFindMediasUseCase(MediaRepository mediaRepository) {
        return new FindMediasUseCase(mediaRepository);
    }

    @Bean
    GetOneMediaUseCase getGetOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new GetOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    ImportOneMediaUseCase getImportOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new ImportOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    ReadOneMediaUseCase getReadOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new ReadOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    UpdateOneMediaUseCase getUpdateOneMediaUseCase(MediaRepository mediaRepository, ProviderRepository providerRepository) {
        return new UpdateOneMediaUseCase(mediaRepository, providerRepository);
    }

    @Bean
    UpsertOneMediaMetadatasUseCase getUpsertOneMediaMetadatasUseCase(MediaRepository mediaRepository) {
        return new UpsertOneMediaMetadatasUseCase(mediaRepository);
    }

}
