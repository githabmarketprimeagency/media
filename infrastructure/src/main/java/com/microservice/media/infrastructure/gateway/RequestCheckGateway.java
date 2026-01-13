package com.microservice.media.infrastructure.gateway;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.model.pagination.RequestCheckPaginatedRepresentation;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.infrastructure.mapper.RequestCheckMapper;
import com.microservice.media.infrastructure.mapper.hateoas.requestcheck.RequestCheckAssembler;
import com.microservice.media.infrastructure.mapper.hateoas.requestcheck.RequestCheckAssemblerMapper;
import com.microservice.media.infrastructure.mapper.hateoas.requestcheck.RequestCheckPaginatedAssemblerMapper;
import com.microservice.media.infrastructure.model.RequestCheckEntity;
import com.microservice.media.infrastructure.querybuilder.CountedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.FilteredResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.PaginatedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.SingleResultQueryBuilder;
import com.microservice.media.infrastructure.repository.RequestCheckRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class RequestCheckGateway implements com.microservice.media.domain.repository.RequestCheckRepository {
    private final RequestCheckRepository jpaRepository;
    private final PaginatedResultQueryBuilder paginatedBuilder;
    private final CountedResultQueryBuilder countedBuilder;
    private final SingleResultQueryBuilder singleResultQueryBuilder;
    private final FilteredResultQueryBuilder filteredResultQueryBuilder;
    private final RequestCheckAssembler assembler;

    @Override
    @Transactional
    public RequestCheck save(RequestCheck requestCheck) {
        RequestCheckEntity requestCheckEntity = RequestCheckMapper.INSTANCE.fromDomain(requestCheck);
        return RequestCheckAssemblerMapper.INSTANCE.toDomain(assembler
                .toModel(jpaRepository.save(requestCheckEntity)));
    }

    @Override
    public Optional<RequestCheck> findOneByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid)
                .map(assembler::toModel)
                .map(RequestCheckAssemblerMapper.INSTANCE::toDomain);
    }

    @Override
    @Transactional
    public void deleteOneByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }

    @Override
    public Integer countBy(List<Criteria> criterias) {
        return countedBuilder.countWithPaginationAndSpecs(criterias, jpaRepository);
    }

    @Override
    public RequestCheckPaginatedRepresentation findBy(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        PagedModel<EntityModel<RequestCheckEntity>> requestChecks = assembler
                .toPagedModel(paginatedBuilder.searchWithPaginationAndSpecs(criterias,
                        pageNumber, pageSize, jpaRepository));
        return RequestCheckPaginatedAssemblerMapper.INSTANCE.toPaginatedRepresentation(requestChecks);
    }

    @Override
    public Integer countInPeriod(String startDate, String endDate, String url, String method, String content, String userIp) {
        OffsetDateTime start = parseDate(startDate);
        OffsetDateTime end = parseDate(endDate);
        return jpaRepository.countInPeriod(start, end, url, method, content, userIp);
    }

    private OffsetDateTime parseDate(String dateStr) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());

        if ("now".equalsIgnoreCase(dateStr)) {
            return now;
        }

        if (dateStr.startsWith("-")) {
            String[] parts = dateStr.substring(1).split(" ");
            int value = Integer.parseInt(parts[0]);
            String unit = parts[1];

            return switch (unit) {
                case "minutes" -> now.minusMinutes(value);
                case "hours" -> now.minusHours(value);
                case "days" -> now.minusDays(value);
                default -> now;
            };
        }

        return OffsetDateTime.parse(dateStr);
    }

    @Override
    public List<RequestCheck> findBy(List<Criteria> criterias) {
        return filteredResultQueryBuilder.searchWithSpecs(criterias, jpaRepository).stream()
                .map(assembler::toModel)
                .map(RequestCheckAssemblerMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public Optional<RequestCheck> findOneBy(List<Criteria> criterias) {
        return singleResultQueryBuilder.searchWithSpecs(criterias, jpaRepository)
                .map(assembler::toModel)
                .map(RequestCheckAssemblerMapper.INSTANCE::toDomain);
    }
}
