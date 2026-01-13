package com.microservice.media.domain.repository;

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.model.pagination.RequestCheckPaginatedRepresentation;
import com.microservice.media.domain.repository.base.CrudPaginatedRepository;

public interface RequestCheckRepository extends CrudPaginatedRepository<RequestCheck, RequestCheckPaginatedRepresentation> {
    Integer countInPeriod(String startDate, String endDate, String url, String method, String content, String userIp);
}
