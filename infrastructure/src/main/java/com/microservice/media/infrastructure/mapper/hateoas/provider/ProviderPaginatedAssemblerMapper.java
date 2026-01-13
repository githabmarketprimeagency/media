package com.microservice.media.infrastructure.mapper.hateoas.provider;

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

import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.model.pagination.ProviderPaginatedRepresentation;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLink;
import com.microservice.media.domain.model.pagination.PaginatedRepresentation.PageNavigationLinks;
import com.microservice.media.infrastructure.mapper.ProviderMapper;
import com.microservice.media.infrastructure.model.ProviderEntity;

@Mapper(componentModel = "spring", uses = {ProviderMapper.class, ProviderAssemblerMapper.class})
public interface ProviderPaginatedAssemblerMapper {

    ProviderPaginatedAssemblerMapper INSTANCE = Mappers.getMapper(ProviderPaginatedAssemblerMapper.class);

    @Mapping(target = "content", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "totalPages", source = "metadata.totalPages")
    @Mapping(target = "pageSize", source = "metadata.size")
    @Mapping(target = "pageNumber", source = "metadata.number")
    @Mapping(target = "totalElements", source = "metadata.totalElements")
    @Mapping(target = "links", ignore = true)
    ProviderPaginatedRepresentation toPaginatedRepresentation(PagedModel<EntityModel<ProviderEntity>> providers);

    @AfterMapping
    default void afterMapping(PagedModel<EntityModel<ProviderEntity>> source,
            @MappingTarget ProviderPaginatedRepresentation target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default List<Provider> mapContent(Collection<EntityModel<ProviderEntity>> content) {
        if (content == null) {
            return Collections.emptyList();
        }
        return content.stream().map(ProviderAssemblerMapper.INSTANCE::toDomain).toList();
    }

    default PageNavigationLinks mapNavigationLinks(PagedModel<EntityModel<ProviderEntity>> providers) {
        PageNavigationLinks links = new PageNavigationLinks();
        if (providers == null) {
            return links;
        }

        links.setSelf(new PageNavigationLink(providers.getRequiredLink("self").getHref()));
        links.setNext(providers.getNextLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setPrevious(providers.getPreviousLink().map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setFirst(providers.getLink(IanaLinkRelations.FIRST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));
        links.setLast(providers.getLink(IanaLinkRelations.LAST).map(link -> new PageNavigationLink(link.getHref())).orElse(null));

        return links;
    }
}
