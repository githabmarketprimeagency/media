package com.microservice.media.ui.mapper.media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MediaDto;
import com.microservice.media.domain.model.Media;

@Mapper
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);
    Media toDomain(MediaDto dto);
    MediaDto fromDomain(Media model);
}
