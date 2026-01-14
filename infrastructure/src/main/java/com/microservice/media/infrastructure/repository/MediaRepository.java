package com.microservice.media.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.microservice.media.infrastructure.model.MediaEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MediaRepository extends
        JpaRepository<MediaEntity, Long>,
        JpaSpecificationExecutor<MediaEntity> {

    Optional<MediaEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
