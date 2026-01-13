package com.microservice.media.ui.mapper.provider;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.NativeProviderCreateRequestDto;
import com.microservice.media.ui.spec.model.SharePointProviderCreateRequestDto;
import com.microservice.media.domain.model.Provider;

@Mapper
public interface CreateProviderMapper {
    CreateProviderMapper INSTANCE = Mappers.getMapper(CreateProviderMapper.class);

    Provider toDomainFromNative(NativeProviderCreateRequestDto dto);
    NativeProviderCreateRequestDto fromDomainToNative(Provider model);

    Provider toDomainFromSharePoint(SharePointProviderCreateRequestDto dto);
    SharePointProviderCreateRequestDto fromDomainToSharePoint(Provider model);
}
