package com.microservice.media.ui.mapper.requestcheck;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.RequestCheckDto;
import com.microservice.media.domain.model.RequestCheck;

@Mapper
public interface RequestCheckMapper {
    RequestCheckMapper INSTANCE = Mappers.getMapper(RequestCheckMapper.class);
    RequestCheck toDomain(RequestCheckDto dto);
    RequestCheckDto fromDomain(RequestCheck model);
}
