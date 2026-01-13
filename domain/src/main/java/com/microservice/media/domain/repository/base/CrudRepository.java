package com.microservice.media.domain.repository.base;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudRepository<T> {

    List<T> findBy(List<Criteria> criterias);

    Integer countBy(List<Criteria> criterias);

    Optional<T> findOneByUuid(UUID uuid);

    Optional<T> findOneBy(List<Criteria> criterias);

    T save(T user);

    void deleteOneByUuid(UUID uuid);

}
