package com.microservice.media.infrastructure.querybuilder;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.microservice.media.domain.repository.base.Criteria;

import java.util.List;
import java.util.Optional;

@Component
public class SingleResultQueryBuilder extends SpecificationsBuilder {

    public <T> Optional<T> searchWithSpecs(
            List<Criteria> criterias,
            JpaSpecificationExecutor<T> repository) {

        Specification<T> spec = buildSpecificationFromCriterias(criterias);

        return repository.findOne(spec);
    }

}