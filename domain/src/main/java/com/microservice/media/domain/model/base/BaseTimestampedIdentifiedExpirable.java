package com.microservice.media.domain.model.base;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseTimestampedIdentifiedExpirable
    extends BaseTimestampedIdentified {
    private OffsetDateTime expiredAt;
    private Integer expiredInSeconds;
}
