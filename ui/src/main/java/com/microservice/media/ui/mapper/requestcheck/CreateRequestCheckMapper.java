package com.microservice.media.ui.mapper.requestcheck;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.RequestCheckCreateRequestDto;
import com.microservice.media.domain.model.RequestCheck;

@Mapper
public interface CreateRequestCheckMapper {
    CreateRequestCheckMapper INSTANCE = Mappers.getMapper(CreateRequestCheckMapper.class);
    RequestCheck toDomain(RequestCheckCreateRequestDto dto);
    RequestCheckCreateRequestDto fromDomain(RequestCheck model);
}
