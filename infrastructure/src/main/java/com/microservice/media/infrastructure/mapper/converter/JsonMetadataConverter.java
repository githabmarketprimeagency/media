package com.microservice.media.infrastructure.mapper.converter;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.media.infrastructure.model.MetadataEntity;

import jakarta.persistence.Converter;

@Converter
public class JsonMetadataConverter extends ObjectToJsonConverter<List<MetadataEntity>> {

    public JsonMetadataConverter() {
        super(new ObjectMapper().getTypeFactory()
                .constructCollectionType(List.class, MetadataEntity.class));
    }
}
