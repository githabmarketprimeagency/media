package com.microservice.media.infrastructure.mapper;

import com.microservice.media.domain.model.Media;
import com.microservice.media.infrastructure.model.MediaEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MetadataMapper.class)
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    @Mapping(target = "metadatas", source = "metadatas")
    @Mapping(target = "links", ignore = true)
    @Mapping(target = "provider", ignore = true)
    Media toDomain(MediaEntity entity);

    @Mapping(target = "metadatas", source = "metadatas")
    MediaEntity fromDomain(Media model);
}
