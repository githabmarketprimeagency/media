package com.microservice.media.domain.model.base;

import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseIdentified {
    private Integer id;
    private UUID uuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseIdentified that)) {
            return false;
        }
        return Objects.equals(
            uuid,
            that.uuid
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
