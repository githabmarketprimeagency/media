package com.microservice.media.infrastructure.mapper;

import java.util.UUID;

public class TypeMapper {

    public static Class<?> toType(String type) {
        return switch (type.toLowerCase()) {
            case "uuid" -> UUID.class;
            default -> null;
        };
    }
}
