package com.microservice.media.infrastructure.mapper.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Converter;

@Converter
public class JsonObjectConverter extends ObjectToJsonConverter<Object> {

    public JsonObjectConverter() {
        super(new ObjectMapper().getTypeFactory().constructType(Object.class));
    }
}
