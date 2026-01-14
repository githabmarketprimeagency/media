package com.microservice.media.domain.model;

import com.microservice.media.domain.model.base.BaseTimestampedIdentified;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends BaseTimestampedIdentified {
    private String name;
    private String providerType;
    private String providerState;
    private String providerAuthType;
    private String providerLocation;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scopes;
    private String tenantId;
    private String apiKey;
    private String impersonatedUserEmail;
    private String driveId;
    private String accessToken;
    private OffsetDateTime accessTokenExpiredAt;
    private Boolean isExpirableAccessToken;
    private String refreshToken;
    private OffsetDateTime refreshTokenExpiredAt;
    /*
    private List<Metadata> metadatas;
    private Object metadatasIndexes;
    */
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Links links;
}
