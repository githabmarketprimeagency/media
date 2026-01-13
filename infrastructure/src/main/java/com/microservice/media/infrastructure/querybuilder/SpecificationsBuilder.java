package com.microservice.media.infrastructure.querybuilder;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.microservice.media.domain.repository.base.Criteria;

public class SpecificationsBuilder {

    protected <T> Specification<T> buildSpecificationFromCriterias(List<Criteria> criterias) {
        List<Specification<T>> specs = criterias.stream()
                .map(this::<T>toSpecification)
                .filter(Objects::nonNull)
                .toList();

        if (specs.isEmpty()) {
            return Specification.where(null);
        }

        return specs.stream().reduce(Specification::and).get();
    }

    @SuppressWarnings("unchecked")
    protected <T> Specification<T> toSpecification(Criteria criteria) {
        String field = criteria.getName();
        Object value = criteria.getValue();

        if (field.equals("orderBy")) {
            String[] splitedValue = value.toString().split(":");
            String fieldName = splitedValue.length > 0 ? splitedValue[0] : null;
            String order = splitedValue.length > 1 ? splitedValue[1] : null;

            OrderingOperation ordering = OrderingOperation.fromString(order);
            return switch (ordering) {
                case ASC ->
                    (Specification<T>) QuerySpecifications.orderByAsc(fieldName);
                case DESC ->
                    (Specification<T>) QuerySpecifications.orderByDesc(fieldName);
                default ->
                    null;
            };
        }

        SearchOperation operation = SearchOperation.fromString(criteria.getCompareason());
        System.out.println("field : " + field + " value : " + value + " operation : " + operation);
        return switch (operation) {
            case EQUAL ->
                (Specification<T>) QuerySpecifications.equal(field, value);
            case NOT_EQUAL ->
                (Specification<T>) QuerySpecifications.notEqual(field, value);
            case LIKE ->
                (Specification<T>) QuerySpecifications.like(field, value);
            case NOT_LIKE ->
                (Specification<T>) QuerySpecifications.notLike(field, value);
            case GT ->
                (Specification<T>) QuerySpecifications.greaterThan(field, (Comparable<? super Object>) value);
            case GTE ->
                (Specification<T>) QuerySpecifications.greaterThanOrEqualTo(field, (Comparable<? super Object>) value);
            case LT ->
                (Specification<T>) QuerySpecifications.lessThan(field, (Comparable<? super Object>) value);
            case LTE ->
                (Specification<T>) QuerySpecifications.lessThanOrEqualTo(field, (Comparable<? super Object>) value);
            default ->
                null;
        };
    }
}
