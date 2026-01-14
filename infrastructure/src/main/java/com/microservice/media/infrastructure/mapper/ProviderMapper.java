package com.microservice.media.infrastructure.mapper;

import com.microservice.media.domain.model.Provider;
import com.microservice.media.infrastructure.model.ProviderEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MetadataMapper.class)
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    @Mapping(target = "metadatas", ignore = true)
    @Mapping(target = "metadatasIndexes", ignore = true)
    @Mapping(target = "links", ignore = true)
    Provider toDomain(ProviderEntity entity);

    @Mapping(target = "metadatas", ignore = true)
    @Mapping(target = "metadatasIndexes", ignore = true)
    ProviderEntity fromDomain(Provider model);
}
