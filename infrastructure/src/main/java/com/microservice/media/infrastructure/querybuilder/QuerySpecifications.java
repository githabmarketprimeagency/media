package com.microservice.media.infrastructure.querybuilder;

import org.springframework.data.jpa.domain.Specification;

public class QuerySpecifications {

    private QuerySpecifications() {}

    public static<T, U> Specification<T> equal(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.<U>get(fieldName), value);
    }

    public static<T, U> Specification<T> notEqual(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
        criteriaBuilder.notEqual(root.<U>get(fieldName), value);
    }

    public static<T, U extends Comparable<? super U>> Specification<T> greaterThan(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.<U>get(fieldName), value);
    }

    public static<T, U extends Comparable<? super U>> Specification<T> greaterThanOrEqualTo(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.<U>get(fieldName), value);
    }

    public static<T, U extends Comparable<? super U>> Specification<T> lessThan(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.<U>get(fieldName), value);
    }

    public static<T, U extends Comparable<? super U>> Specification<T> lessThanOrEqualTo(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.<U>get(fieldName), value);
    }

    public static<T, U> Specification<T> like(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(fieldName), "%" + value + "%");
    }

    public static<T, U> Specification<T> notLike(String fieldName, U value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notLike(root.get(fieldName), "%" + value + "%");
    }

    public static <T> Specification<T> orderByAsc(String fieldName) {
        return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.asc(root.get(fieldName)));
                return null;
        };
    }

    public static <T> Specification<T> orderByDesc(String fieldName) {
        return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.desc(root.get(fieldName)));
                return null;
        };
    }

}