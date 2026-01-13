package com.microservice.media.infrastructure.querybuilder;

public enum OrderingOperation {
    ASC, DESC;

    public static OrderingOperation fromString(String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Opération non supportée : " + operation);
        }
        return switch (operation.toUpperCase()) {
            case "ASC" -> ASC;
            case "DESC" -> DESC;
            default -> throw new IllegalArgumentException("Opération non supportée : " + operation);
        };
    }
}