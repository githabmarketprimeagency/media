package com.microservice.media.infrastructure.mapper;

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.infrastructure.model.RequestCheckEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MetadataMapper.class)
public interface RequestCheckMapper {
    RequestCheckMapper INSTANCE = Mappers.getMapper(RequestCheckMapper.class);

    @Mapping(target = "metadatas", source = "metadatas")
    @Mapping(target = "links", ignore = true)
    RequestCheck toDomain(RequestCheckEntity entity);

    @Mapping(target = "metadatas", source = "metadatas")
    RequestCheckEntity fromDomain(RequestCheck model);
}
