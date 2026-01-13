package com.microservice.media.ui.mapper.requestcheck;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.RequestCheckPaginatedRepresentationDto;
import com.microservice.media.domain.model.pagination.RequestCheckPaginatedRepresentation;

@Mapper
public interface RequestCheckPaginatedRepresentationMapper {
    RequestCheckPaginatedRepresentationMapper INSTANCE = Mappers.getMapper(RequestCheckPaginatedRepresentationMapper.class);

    RequestCheckPaginatedRepresentation toDomain(RequestCheckPaginatedRepresentationDto dto);
    RequestCheckPaginatedRepresentationDto fromDomain(RequestCheckPaginatedRepresentation model);
}
