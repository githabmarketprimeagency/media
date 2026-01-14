package com.microservice.media.infrastructure.gateway;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.pagination.MediaPaginatedRepresentation;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.infrastructure.mapper.MediaMapper;
import com.microservice.media.infrastructure.mapper.hateoas.media.MediaAssembler;
import com.microservice.media.infrastructure.mapper.hateoas.media.MediaAssemblerMapper;
import com.microservice.media.infrastructure.mapper.hateoas.media.MediaPaginatedAssemblerMapper;
import com.microservice.media.infrastructure.model.MediaEntity;
import com.microservice.media.infrastructure.querybuilder.CountedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.FilteredResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.PaginatedResultQueryBuilder;
import com.microservice.media.infrastructure.querybuilder.SingleResultQueryBuilder;
import com.microservice.media.infrastructure.repository.MediaRepository;

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
public class MediaGateway implements com.microservice.media.domain.repository.MediaRepository {
    private final MediaRepository jpaRepository;
    private final PaginatedResultQueryBuilder paginatedBuilder;
    private final CountedResultQueryBuilder countedBuilder;
    private final SingleResultQueryBuilder singleResultQueryBuilder;
    private final FilteredResultQueryBuilder filteredResultQueryBuilder;
    private final MediaAssembler assembler;

    @Override
    @Transactional
    public Media save(Media media) {
        MediaEntity mediaEntity = MediaMapper.INSTANCE.fromDomain(media);
        return MediaAssemblerMapper.INSTANCE.toDomain(assembler
                .toModel(jpaRepository.save(mediaEntity)));
    }

    @Override
    public Optional<Media> findOneByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid)
                .map(assembler::toModel)
                .map(MediaAssemblerMapper.INSTANCE::toDomain);
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
    public MediaPaginatedRepresentation findBy(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        PagedModel<EntityModel<MediaEntity>> medias = assembler
                .toPagedModel(paginatedBuilder.searchWithPaginationAndSpecs(criterias,
                        pageNumber, pageSize, jpaRepository));
        return MediaPaginatedAssemblerMapper.INSTANCE.toPaginatedRepresentation(medias);
    }

    @Override
    public List<Media> findBy(List<Criteria> criterias) {
        return filteredResultQueryBuilder.searchWithSpecs(criterias, jpaRepository).stream()
                .map(assembler::toModel)
                .map(MediaAssemblerMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public Optional<Media> findOneBy(List<Criteria> criterias) {
        return singleResultQueryBuilder.searchWithSpecs(criterias, jpaRepository)
                .map(assembler::toModel)
                .map(MediaAssemblerMapper.INSTANCE::toDomain);
    }
}
