package com.microservice.media.domain.repository.base;

import com.microservice.media.domain.model.pagination.PaginatedRepresentation;

import java.util.List;

public interface CrudPaginatedRepository<T, P extends PaginatedRepresentation<T>> extends CrudRepository<T> {
    P findBy(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy);
}
