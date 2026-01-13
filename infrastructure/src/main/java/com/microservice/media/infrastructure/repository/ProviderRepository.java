package com.microservice.media.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.microservice.media.infrastructure.model.ProviderEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProviderRepository extends
        JpaRepository<ProviderEntity, Long>,
        JpaSpecificationExecutor<ProviderEntity> {

    Optional<ProviderEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
