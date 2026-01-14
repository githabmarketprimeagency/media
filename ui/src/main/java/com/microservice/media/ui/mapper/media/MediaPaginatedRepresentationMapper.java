package com.microservice.media.ui.mapper.media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MediaPaginatedRepresentationDto;
import com.microservice.media.domain.model.pagination.MediaPaginatedRepresentation;

@Mapper
public interface MediaPaginatedRepresentationMapper {
    MediaPaginatedRepresentationMapper INSTANCE = Mappers.getMapper(MediaPaginatedRepresentationMapper.class);
    MediaPaginatedRepresentation toDomain(MediaPaginatedRepresentationDto dto);
    MediaPaginatedRepresentationDto fromDomain(MediaPaginatedRepresentation model);
}
