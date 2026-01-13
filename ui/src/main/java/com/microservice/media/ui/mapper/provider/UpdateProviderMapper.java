package com.microservice.media.ui.mapper.provider;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.NativeProviderUpdateRequestDto;
import com.microservice.media.ui.spec.model.SharePointProviderUpdateRequestDto;
import com.microservice.media.domain.model.Provider;

@Mapper
public interface UpdateProviderMapper {
    UpdateProviderMapper INSTANCE = Mappers.getMapper(UpdateProviderMapper.class);

    Provider toDomainFromNative(NativeProviderUpdateRequestDto dto);
    NativeProviderUpdateRequestDto fromDomainToNative(Provider model);

    Provider toDomainFromSharePoint(SharePointProviderUpdateRequestDto dto);
    SharePointProviderUpdateRequestDto fromDomainToSharePoint(Provider model);
}
