package com.microservice.media.infrastructure.mapper.hateoas.media;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;

import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.pagination.MediaPaginatedRepresentation;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLink;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLinks;
import com.microservice.media.infrastructure.mapper.MediaMapper;
import com.microservice.media.infrastructure.model.MediaEntity;

@Mapper(componentModel = "spring", uses = {MediaMapper.class, MediaAssemblerMapper.class})
public interface MediaPaginatedAssemblerMapper {

    MediaPaginatedAssemblerMapper INSTANCE = Mappers.getMapper(MediaPaginatedAssemblerMapper.class);

    @Mapping(target = "content", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "totalPages", source = "metadata.totalPages")
    @Mapping(target = "pageSize", source = "metadata.size")
    @Mapping(target = "pageNumber", source = "metadata.number")
    @Mapping(target = "totalElements", source = "metadata.totalElements")
    @Mapping(target = "links", ignore = true)
    MediaPaginatedRepresentation toPaginatedRepresentation(PagedModel<EntityModel<MediaEntity>> medias);

    @AfterMapping
    default void afterMapping(PagedModel<EntityModel<MediaEntity>> source,
            @MappingTarget MediaPaginatedRepresentation target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default List<Media> mapContent(Collection<EntityModel<MediaEntity>> content) {
        if (content == null) {
            return Collections.emptyList();
        }
        return content.stream().map(MediaAssemblerMapper.INSTANCE::toDomain).toList();
    }

    default PageNavigationLinks mapNavigationLinks(PagedModel<EntityModel<MediaEntity>> medias) {
        PageNavigationLinks links = new PageNavigationLinks();
        if (medias == null) {
            return links;
        }

        links.setSelf(new PageNavigationLink(medias.getRequiredLink("self").getHref()));
        links.setNext(medias.getNextLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setPrevious(medias.getPreviousLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setFirst(medias.getLink(IanaLinkRelations.FIRST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setLast(medias.getLink(IanaLinkRelations.LAST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));

        return links;
    }
}
