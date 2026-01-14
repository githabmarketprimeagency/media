package com.microservice.media.infrastructure.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import com.microservice.media.infrastructure.model.base.BaseTimestampedIdentified;

import java.util.List;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaEntity extends BaseTimestampedIdentified {
    @Column(name = "entity_uuid", length = 300, nullable = false)
    private String entityUuid;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "extension", length = 30)
    private String extension;

    @Column(name = "mime_type", length = 255, nullable = false)
    private String mimeType;

    @Column(name = "size", nullable = false)
    private Double size;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "provider_uuid", length = 1000, nullable = false)
    private String providerUuid;

    @Column(name = "provider_path", length = 1000, nullable = false)
    private String providerPath;

    /*
    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private List<MetadataEntity> metadatas;

    @Type(JsonType.class)
    @Column(name = "metadatas_indexes", columnDefinition = "jsonb")
    private Object metadatasIndexes;
    */

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Column(name = "deleted_by", length = 255)
    private String deletedBy;
}
