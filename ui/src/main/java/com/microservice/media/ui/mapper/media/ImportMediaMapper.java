package com.microservice.media.ui.mapper.media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.ui.spec.model.MediaImportRequestDto;
import com.microservice.media.domain.model.Media;

@Mapper
public interface ImportMediaMapper {
    ImportMediaMapper INSTANCE = Mappers.getMapper(ImportMediaMapper.class);

    Media toDomain(MediaImportRequestDto dto);
    MediaImportRequestDto fromDomain(Media model);
}
