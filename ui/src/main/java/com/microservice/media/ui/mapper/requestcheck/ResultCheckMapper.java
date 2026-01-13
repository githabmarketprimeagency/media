package com.microservice.media.ui.mapper.requestcheck;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.ResultCheckDto;
import com.microservice.media.domain.model.ResultCheck;

@Mapper
public interface ResultCheckMapper {
    ResultCheckMapper INSTANCE = Mappers.getMapper(ResultCheckMapper.class);
    ResultCheck toDomain(ResultCheckDto dto);
    ResultCheckDto fromDomain(ResultCheck model);
}
