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
@Table(name = "request_check")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckEntity extends BaseTimestampedIdentified {
    @Column(name = "entity_uuid", length = 300)
    private String entityUuid;

    @Column(name = "user_ip", length = 255)
    private String userIp;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "headers", columnDefinition = "TEXT")
    private String headers;

    @Column(name = "method", length = 20, nullable = false)
    private String method;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @Column(name = "referrer", length = 500)
    private String referrer;

    @Column(name = "author", length = 255)
    private String author;

    @Type(JsonType.class)
    @Column(name = "same_request_occurence_range", columnDefinition = "jsonb")
    private List<Integer> sameRequestOccurenceRange;

    @Type(JsonType.class)
    @Column(name = "same_request_period_range", columnDefinition = "jsonb")
    private List<Integer> sameRequestPeriodRange;

    @Type(JsonType.class)
    @Column(name = "same_user_occurence_range", columnDefinition = "jsonb")
    private List<Integer> sameUserOccurenceRange;

    @Type(JsonType.class)
    @Column(name = "same_user_period_range", columnDefinition = "jsonb")
    private List<Integer> sameUserPeriodRange;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private List<MetadataEntity> metadatas;
}
