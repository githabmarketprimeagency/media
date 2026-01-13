package com.microservice.media.infrastructure.querybuilder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.microservice.media.domain.repository.base.Criteria;

import java.util.List;

@Component
public class PaginatedResultQueryBuilder extends SpecificationsBuilder {

    public <T> Page<T> searchWithPaginationAndSpecs(
            List<Criteria> criterias,
            Integer pageNumber,
            Integer pageSize,
            JpaSpecificationExecutor<T> repository) {

        Specification<T> spec = buildSpecificationFromCriterias(criterias);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findAll(spec, pageable);
    }

}