package com.microservice.media.infrastructure.mapper;

import com.microservice.media.domain.model.Metadata;
import com.microservice.media.infrastructure.model.MetadataEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MetadataMapper {
    MetadataMapper INSTANCE = Mappers.getMapper(MetadataMapper.class);

    Metadata toDomain(MetadataEntity entity);

    MetadataEntity fromDomain(Metadata model);

    List<Metadata> toDomainList(List<MetadataEntity> entities);

    List<MetadataEntity> fromDomainList(List<Metadata> models);
}
