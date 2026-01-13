package com.microservice.media.domain.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(UUID uuid) {
        super("Entity not found with uuid: " + uuid);
    }
}
