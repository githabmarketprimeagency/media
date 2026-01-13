package com.microservice.media.domain.model.base;

import java.time.OffsetDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseTimestampedIdentified
    extends BaseIdentified {
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseIdentified that)) {
            return false;
        }
        return Objects.equals(
            getUuid(),
            that.getUuid()
        );
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

}
