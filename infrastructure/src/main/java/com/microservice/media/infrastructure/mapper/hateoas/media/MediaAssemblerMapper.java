package com.microservice.media.infrastructure.mapper.hateoas.media;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;

import com.microservice.media.domain.model.Links;
import com.microservice.media.domain.model.Media;
import com.microservice.media.infrastructure.mapper.MediaMapper;
import com.microservice.media.infrastructure.model.MediaEntity;

import jakarta.inject.Named;

@Mapper(componentModel = "spring", uses = {MediaMapper.class})
public interface MediaAssemblerMapper {

    MediaAssemblerMapper INSTANCE = Mappers.getMapper(MediaAssemblerMapper.class);

    @Mapping(target = ".", source = "content", qualifiedByName = "mapContent")
    @Mapping(target = "links", ignore = true)
    Media toDomain(EntityModel<MediaEntity> medias);

    @AfterMapping
    default void afterMapping(EntityModel<MediaEntity> source,
            @MappingTarget Media target) {
        if (source != null) {
            target.setLinks(mapNavigationLinks(source));
        }
    }

    @Named("mapContent")
    default Media mapContent(MediaEntity content) {
        if (content == null) {
            return null;
        }
        return MediaMapper.INSTANCE.toDomain(content);
    }

    default Links mapNavigationLinks(EntityModel<MediaEntity> medias) {
        Links links = new Links();
        if (medias == null) {
            return links;
        }
        links.setSelf(new Links.Link(medias.getRequiredLink("self").getHref()));
        links.setRead(medias.getLink("read").map(link -> new Links.Link(link.getHref())).orElse(null));
        links.setDownload(medias.getLink("download").map(link -> new Links.Link(link.getHref())).orElse(null));
        links.setUpdate(medias.getLink("update").map(link -> new Links.Link(link.getHref())).orElse(null));
        links.setDelete(medias.getLink("delete").map(link -> new Links.Link(link.getHref())).orElse(null));

        return links;
    }
}
