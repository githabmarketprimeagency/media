package com.microservice.media.ui.mapper.media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MediaUpdateRequestDto;
import com.microservice.media.domain.model.Media;

@Mapper
public interface UpdateMediaMapper {
    UpdateMediaMapper INSTANCE = Mappers.getMapper(UpdateMediaMapper.class);

    Media toDomain(MediaUpdateRequestDto dto);
    MediaUpdateRequestDto fromDomain(Media model);
}
