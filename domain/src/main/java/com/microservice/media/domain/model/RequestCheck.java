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
public class RequestCheck extends BaseTimestampedIdentified {
    private String entityUuid;
    private String userIp;
    private String userAgent;
    private String headers;
    private String method;
    private String content;
    private String url;
    private String referrer;
    private String author;
    private List<Integer> sameRequestOccurenceRange;
    private List<Integer> sameRequestPeriodRange;
    private List<Integer> sameUserOccurenceRange;
    private List<Integer> sameUserPeriodRange;
    private List<Metadata> metadatas;
    private Links links;
}
