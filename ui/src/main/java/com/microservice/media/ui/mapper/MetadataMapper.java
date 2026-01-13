package com.microservice.media.ui.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MetadataDto;
import com.microservice.media.domain.model.Metadata;

@Mapper
public interface MetadataMapper {
    MetadataMapper INSTANCE = Mappers.getMapper(MetadataMapper.class);
    Metadata toDomain(MetadataDto dto);
    MetadataDto fromDomain(Metadata model);
    List<Metadata> toDomain(List<MetadataDto> dto);
    List<MetadataDto> fromDomain(List<Metadata> model);
}
