package com.microservice.media.domain.model;

import com.microservice.media.domain.model.base.BaseTimestampedIdentified;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media extends BaseTimestampedIdentified {
    private String entityUuid;
    private String name;
    private String extension;
    private String mimeType;
    private Double size;
    private String description;
    private String providerUuid;
    private String providerPath;
    private List<Metadata> metadatas;
    private Object metadatasIndexes;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Links links;
    private Provider provider;
}
