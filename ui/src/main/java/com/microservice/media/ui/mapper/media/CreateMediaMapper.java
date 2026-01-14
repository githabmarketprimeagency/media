package com.microservice.media.ui.mapper.media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MediaCreateRequestDto;
import com.microservice.media.domain.model.Media;

@Mapper
public interface CreateMediaMapper {
    CreateMediaMapper INSTANCE = Mappers.getMapper(CreateMediaMapper.class);

    Media toDomain(MediaCreateRequestDto dto);
    MediaCreateRequestDto fromDomain(Media model);
}
