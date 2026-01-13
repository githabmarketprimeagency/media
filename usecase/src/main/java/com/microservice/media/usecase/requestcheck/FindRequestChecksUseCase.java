package com.microservice.media.usecase.requestcheck;

import lombok.AllArgsConstructor;

import java.util.List;

import com.microservice.media.domain.model.pagination.RequestCheckPaginatedRepresentation;
import com.microservice.media.domain.repository.RequestCheckRepository;
import com.microservice.media.domain.repository.base.Criteria;

@AllArgsConstructor
public class FindRequestChecksUseCase {
    private final RequestCheckRepository requestCheckRepository;

    public RequestCheckPaginatedRepresentation handle(List<Criteria> criteria) {
        return handle(criteria, 0, 10, null);
    }

    public RequestCheckPaginatedRepresentation handle(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        return requestCheckRepository.findBy(criterias, pageNumber, pageSize, orderBy);
    }
}
