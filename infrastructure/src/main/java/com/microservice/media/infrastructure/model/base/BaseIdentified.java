package com.microservice.media.infrastructure.model.base;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseIdentified {

    @Id
    @Column(
        name = "id",
        updatable = false,
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(
        name = "uuid",
        unique = true,
        updatable = false,
        nullable = false
    )
    protected UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

}
