package com.microservice.media.infrastructure.mapper.hateoas.media;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import com.microservice.media.infrastructure.model.MediaEntity;

import lombok.AllArgsConstructor;

@Component("mediaAssembler")
@AllArgsConstructor
public class MediaAssembler {

    private final MediaModelAssembler mediaModelAssembler;
    private final PagedResourcesAssembler<MediaEntity> pagedAssembler;

    public PagedModel<EntityModel<MediaEntity>> toPagedModel(Page<MediaEntity> medias) {
        return pagedAssembler.toModel(medias, mediaModelAssembler);
    }

    public EntityModel<MediaEntity> toModel(MediaEntity media) {
        return mediaModelAssembler.toModel(media);
    }
}
