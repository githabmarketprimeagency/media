package com.microservice.media.infrastructure.model.base;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseTimestampedIdentifiedExpirable
    extends BaseTimestampedIdentified {

    @Column(name = "expired_at")
    protected OffsetDateTime expiredAt;

    @Column(name = "expired_in_seconds")
    protected Integer expiredInSeconds;

}
