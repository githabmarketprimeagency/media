package com.microservice.media.infrastructure.querybuilder;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.microservice.media.domain.repository.base.Criteria;

import java.util.List;

@Component
public class FilteredResultQueryBuilder extends SpecificationsBuilder {

    public <T> List<T> searchWithSpecs(
            List<Criteria> criterias,
            JpaSpecificationExecutor<T> repository) {

        Specification<T> spec = buildSpecificationFromCriterias(criterias);

        return repository.findAll(spec);
    }

}