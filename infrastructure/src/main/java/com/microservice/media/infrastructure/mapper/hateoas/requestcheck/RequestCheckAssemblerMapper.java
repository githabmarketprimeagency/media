package com.microservice.media.infrastructure.mapper.hateoas.requestcheck;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;

import com.microservice.media.domain.model.Links;
import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.infrastructure.mapper.RequestCheckMapper;
import com.microservice.media.infrastructure.model.RequestCheckEntity;

import jakarta.inject.Named;

@Mapper(componentModel = "spring", uses = {RequestCheckMapper.class})
public interface RequestCheckAssemblerMapper {

    RequestCheckAssemblerMapper INSTANCE = Mappers.getMapper(RequestCheckAssemblerMapper.class);

    @Mapping(target = ".", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "links", ignore = true)
    RequestCheck toDomain(EntityModel<RequestCheckEntity> requestChecks);

    @AfterMapping
    default void afterMapping(EntityModel<RequestCheckEntity> source,
            @MappingTarget RequestCheck target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default RequestCheck mapContent(RequestCheckEntity content) {
        if (content == null) {
            return null;
        }
        return RequestCheckMapper.INSTANCE.toDomain(content);
    }

    default Links mapNavigationLinks(EntityModel<RequestCheckEntity> requestchecks) {
        Links links = new Links();
        if (requestchecks == null) {
            return links;
        }
        links.setSelf(new Links.Link(requestchecks.getRequiredLink("self").getHref()));
        links.setDelete(requestchecks.getLink("delete").map(link -> new Links.Link(link.getHref())).orElse(null));

        return links;
    }
}
