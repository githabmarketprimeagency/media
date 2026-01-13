package com.microservice.media.domain.exception;

public class AlreadyExistsException
    extends InvalidInputException {

    public static final String EXISTING_ENTITY_MESSAGE = "A %s already exists with provided %s: %s";

    public AlreadyExistsException(
        String entity,
        String field,
        Object value
    ) {
        super(
            String.format(
                EXISTING_ENTITY_MESSAGE,
                entity,
                field,
                value
            )
        );
    }
}
