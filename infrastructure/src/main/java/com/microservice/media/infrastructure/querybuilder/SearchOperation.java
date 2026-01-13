package com.microservice.media.infrastructure.querybuilder;

public enum SearchOperation {
    EQUAL, NOT_EQUAL, LIKE, NOT_LIKE,
    GT, GTE, LT, LTE;

    public static SearchOperation fromString(String operation) {
        return switch (operation.toUpperCase()) {
            case "EQUAL" -> EQUAL;
            case "NOT_EQUAL" -> NOT_EQUAL;
            case "LIKE" -> LIKE;
            case "NOT_LIKE" -> NOT_LIKE;
            case "GT" -> GT;
            case "GTE" -> GTE;
            case "LT" -> LT;
            case "LTE" -> LTE;
            default -> throw new IllegalArgumentException("Opération non supportée : " + operation);
        };
    }
}