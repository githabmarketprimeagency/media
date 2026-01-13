package com.microservice.media.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.media.infrastructure.model.RequestCheckEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestCheckRepository extends
        JpaRepository<RequestCheckEntity, Long>,
        JpaSpecificationExecutor<RequestCheckEntity> {

    Optional<RequestCheckEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    @Query("SELECT COUNT(r) FROM RequestCheckEntity r WHERE " +
           "r.createdAt >= :startDate AND r.createdAt <= :endDate " +
           "AND (:url IS NULL OR r.url = :url) " +
           "AND (:method IS NULL OR r.method = :method) " +
           "AND (:content IS NULL OR r.content = :content) " +
           "AND (:userIp IS NULL OR r.userIp = :userIp)")
    Integer countInPeriod(
            @Param("startDate") OffsetDateTime startDate,
            @Param("endDate") OffsetDateTime endDate,
            @Param("url") String url,
            @Param("method") String method,
            @Param("content") String content,
            @Param("userIp") String userIp
    );
}
