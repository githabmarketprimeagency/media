package com.microservice.media.ui.mapper.provider;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.ProviderPaginatedRepresentationDto;
import com.microservice.media.domain.model.pagination.ProviderPaginatedRepresentation;

@Mapper
public interface ProviderPaginatedRepresentationMapper {
    ProviderPaginatedRepresentationMapper INSTANCE = Mappers.getMapper(ProviderPaginatedRepresentationMapper.class);

    ProviderPaginatedRepresentation toDomain(ProviderPaginatedRepresentationDto dto);
    ProviderPaginatedRepresentationDto fromDomain(ProviderPaginatedRepresentation model);
}
