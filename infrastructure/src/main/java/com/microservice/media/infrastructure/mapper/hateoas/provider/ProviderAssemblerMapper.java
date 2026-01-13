package com.microservice.media.infrastructure.mapper.hateoas.provider;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;

import com.microservice.media.domain.model.Links;
import com.microservice.media.domain.model.Provider;
import com.microservice.media.infrastructure.mapper.ProviderMapper;
import com.microservice.media.infrastructure.model.ProviderEntity;

import jakarta.inject.Named;

@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface ProviderAssemblerMapper {

    ProviderAssemblerMapper INSTANCE = Mappers.getMapper(ProviderAssemblerMapper.class);

    @Mapping(target = ".", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "links", ignore = true)
    Provider toDomain(EntityModel<ProviderEntity> providers);

    @AfterMapping
    default void afterMapping(EntityModel<ProviderEntity> source,
            @MappingTarget Provider target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default Provider mapContent(ProviderEntity content) {
        if (content == null) {
            return null;
        }
        return ProviderMapper.INSTANCE.toDomain(content);
    }

    default Links mapNavigationLinks(EntityModel<ProviderEntity> providers) {
        Links links = new Links();
        if (providers == null) {
            return links;
        }
        links.setSelf(new Links.Link(providers.getRequiredLink("self").getHref()));
        links.setUpdate(providers.getLink("update").map(link -> new Links.Link(link.getHref())).orElse(null));
        links.setDelete(providers.getLink("delete").map(link -> new Links.Link(link.getHref())).orElse(null));

        return links;
    }
}
