package com.microservice.media.usecase.provider;

import lombok.AllArgsConstructor;

import java.util.List;

import com.microservice.media.domain.model.pagination.ProviderPaginatedRepresentation;
import com.microservice.media.domain.repository.ProviderRepository;
import com.microservice.media.domain.repository.base.Criteria;

@AllArgsConstructor
public class FindProvidersUseCase {
    private final ProviderRepository providerRepository;

    public ProviderPaginatedRepresentation handle(List<Criteria> criteria) {
        return handle(criteria, 0, 10, null);
    }

    public ProviderPaginatedRepresentation handle(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        return providerRepository.findBy(criterias, pageNumber, pageSize, orderBy);
    }
}
