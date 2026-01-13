package com.microservice.media.infrastructure.mapper.hateoas.requestcheck;

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

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.model.pagination.RequestCheckPaginatedRepresentation;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLink;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLinks;
import com.microservice.media.infrastructure.mapper.RequestCheckMapper;
import com.microservice.media.infrastructure.model.RequestCheckEntity;

@Mapper(componentModel = "spring", uses = {RequestCheckMapper.class, RequestCheckAssemblerMapper.class})
public interface RequestCheckPaginatedAssemblerMapper {

    RequestCheckPaginatedAssemblerMapper INSTANCE = Mappers.getMapper(RequestCheckPaginatedAssemblerMapper.class);

    @Mapping(target = "content", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "totalPages", source = "metadata.totalPages")
    @Mapping(target = "pageSize", source = "metadata.size")
    @Mapping(target = "pageNumber", source = "metadata.number")
    @Mapping(target = "totalElements", source = "metadata.totalElements")
    @Mapping(target = "links", ignore = true)
    RequestCheckPaginatedRepresentation toPaginatedRepresentation(PagedModel<EntityModel<RequestCheckEntity>> requestChecks);

    @AfterMapping
    default void afterMapping(PagedModel<EntityModel<RequestCheckEntity>> source,
            @MappingTarget RequestCheckPaginatedRepresentation target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default List<RequestCheck> mapContent(Collection<EntityModel<RequestCheckEntity>> content) {
        if (content == null) {
            return Collections.emptyList();
        }
        return content.stream().map(RequestCheckAssemblerMapper.INSTANCE::toDomain).toList();
    }

    default PageNavigationLinks mapNavigationLinks(PagedModel<EntityModel<RequestCheckEntity>> requestChecks) {
        PageNavigationLinks links = new PageNavigationLinks();
        if (requestChecks == null) {
            return links;
        }

        links.setSelf(new PageNavigationLink(requestChecks.getRequiredLink("self").getHref()));
        links.setNext(requestChecks.getNextLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setPrevious(requestChecks.getPreviousLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setFirst(requestChecks.getLink(IanaLinkRelations.FIRST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setLast(requestChecks.getLink(IanaLinkRelations.LAST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));

        return links;
    }
}
