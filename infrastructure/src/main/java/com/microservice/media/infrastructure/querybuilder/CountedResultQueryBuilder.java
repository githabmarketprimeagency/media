package com.microservice.media.infrastructure.querybuilder;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.microservice.media.domain.repository.base.Criteria;

import java.util.List;

@Component
public class CountedResultQueryBuilder extends SpecificationsBuilder {

    public <T> int countWithPaginationAndSpecs(List<Criteria> criterias, JpaSpecificationExecutor<T> repository) {
        Specification<T> spec = buildSpecificationFromCriterias(criterias);
        return (int) repository.count(spec);
    }

}