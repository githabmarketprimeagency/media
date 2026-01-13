package com.microservice.media.domain.repository;

import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.model.pagination.ProviderPaginatedRepresentation;
import com.microservice.media.domain.repository.base.CrudPaginatedRepository;

public interface ProviderRepository extends CrudPaginatedRepository<Provider, ProviderPaginatedRepresentation> {
}
