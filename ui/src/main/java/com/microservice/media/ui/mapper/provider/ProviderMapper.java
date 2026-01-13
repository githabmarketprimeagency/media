package com.microservice.media.ui.mapper.provider;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.ProviderDto;
import com.microservice.media.domain.model.Provider;

@Mapper
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);
    Provider toDomain(ProviderDto dto);
    ProviderDto fromDomain(Provider model);
}
