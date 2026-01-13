package com.microservice.media.infrastructure.mapper.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * A generic AttributeConverter to serialize any object to a JSON string for
 * database storage and deserialize it back.
 *
 * @param <T> The type of the object to be converted.
 */
@Converter
public abstract class ObjectToJsonConverter<T> implements AttributeConverter<T, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JavaType targetClass;

    public ObjectToJsonConverter(JavaType targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Log the error or throw a runtime exception
            throw new RuntimeException("Could not serialize object to JSON string.", e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, targetClass);
        } catch (IOException e) {
            // Log the error or throw a runtime exception
            throw new RuntimeException("Could not deserialize JSON string to object.", e);
        }
    }
}
