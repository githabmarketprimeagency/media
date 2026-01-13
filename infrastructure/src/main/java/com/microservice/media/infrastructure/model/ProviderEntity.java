package com.microservice.media.infrastructure.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import com.microservice.media.infrastructure.model.base.BaseTimestampedIdentified;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "provider")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderEntity extends BaseTimestampedIdentified {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "provider_type", length = 50, nullable = false)
    private String providerType;

    @Column(name = "provider_state", length = 50, nullable = false)
    private String providerState;

    @Column(name = "provider_auth_type", length = 100, nullable = false)
    private String providerAuthType;

    @Column(name = "provider_location", length = 50, nullable = false)
    private String providerLocation;

    @Column(name = "client_id", length = 255)
    private String clientId;

    @Column(name = "client_secret", length = 255)
    private String clientSecret;

    @Column(name = "redirect_uri", length = 500)
    private String redirectUri;

    @Column(name = "scopes", length = 255)
    private String scopes;

    @Column(name = "tenant_id", length = 500)
    private String tenantId;

    @Column(name = "api_key", length = 500)
    private String apiKey;

    @Column(name = "impersonated_user_email", length = 100)
    private String impersonatedUserEmail;

    @Column(name = "drive_id", length = 500)
    private String driveId;

    @Column(name = "access_token", length = 4000)
    private String accessToken;

    @Column(name = "access_token_expired_at")
    private OffsetDateTime accessTokenExpiredAt;

    @Column(name = "is_expirable_access_token")
    private Boolean isExpirableAccessToken;

    @Column(name = "refresh_token", length = 4000)
    private String refreshToken;

    @Column(name = "refresh_token_expired_at")
    private OffsetDateTime refreshTokenExpiredAt;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private List<MetadataEntity> metadatas;

    @Type(JsonType.class)
    @Column(name = "metadatas_indexes", columnDefinition = "jsonb")
    private Object metadatasIndexes;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Column(name = "deleted_by", length = 255)
    private String deletedBy;
}
