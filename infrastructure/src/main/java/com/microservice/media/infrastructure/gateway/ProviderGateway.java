package com.microservice.media.infrastructure.gateway;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.model.pagination.ProviderPaginatedRepresentation;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.infrastructure.mapper.ProviderMapper;
import com.microservice.media.infrastructure.mapper.hateoas.provider.ProviderAssembler;
import com.microservice.media.infrastructure.mapper.hateoas.provider.ProviderAssemblerMapper;
import com.microservice.media.infrastructure.mapper.hateoas.provider.ProviderPaginatedAssemblerMapper;
import com.microservice.media.infrastructure.model.ProviderEntity;
import com.microservice.media.infrastructure.querybuilder.CountedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.FilteredResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.PaginatedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.SingleResultQueryBuilder;
import com.microservice.media.infrastructure.repository.ProviderRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class ProviderGateway implements com.microservice.media.domain.repository.ProviderRepository {
    private final ProviderRepository jpaRepository;
    private final PaginatedResultQueryBuilder paginatedBuilder;
    private final CountedResultQueryBuilder countedBuilder;
    private final SingleResultQueryBuilder singleResultQueryBuilder;
    private final FilteredResultQueryBuilder filteredResultQueryBuilder;
    private final ProviderAssembler assembler;

    @Override
    @Transactional
    public Provider save(Provider provider) {
        ProviderEntity providerEntity = ProviderMapper.INSTANCE.fromDomain(provider);
        return ProviderAssemblerMapper.INSTANCE.toDomain(assembler
                .toModel(jpaRepository.save(providerEntity)));
    }

    @Override
    public Optional<Provider> findOneByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid)
                .map(assembler::toModel)
                .map(ProviderAssemblerMapper.INSTANCE::toDomain);
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
    public ProviderPaginatedRepresentation findBy(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        PagedModel<EntityModel<ProviderEntity>> providers = assembler
                .toPagedModel(paginatedBuilder.searchWithPaginationAndSpecs(criterias,
                        pageNumber, pageSize, jpaRepository));
        return ProviderPaginatedAssemblerMapper.INSTANCE.toPaginatedRepresentation(providers);
    }

    @Override
    public List<Provider> findBy(List<Criteria> criterias) {
        return filteredResultQueryBuilder.searchWithSpecs(criterias, jpaRepository).stream()
                .map(assembler::toModel)
                .map(ProviderAssemblerMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public Optional<Provider> findOneBy(List<Criteria> criterias) {
        return singleResultQueryBuilder.searchWithSpecs(criterias, jpaRepository)
                .map(assembler::toModel)
                .map(ProviderAssemblerMapper.INSTANCE::toDomain);
    }
}
