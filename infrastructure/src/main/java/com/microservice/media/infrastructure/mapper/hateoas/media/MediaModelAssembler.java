package com.microservice.media.infrastructure.mapper.hateoas.media;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.microservice.media.ui.presenter.MediaPresenterImpl;
import com.microservice.media.infrastructure.model.MediaEntity;

@Component("mediaModelAssembler")
public class MediaModelAssembler
        implements RepresentationModelAssembler<MediaEntity, EntityModel<MediaEntity>> {

    @Override
    public EntityModel<MediaEntity> toModel(MediaEntity media) {
        return EntityModel.of(media,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MediaPresenterImpl.class)
                .getOneMedia(media.getUuid())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(MediaPresenterImpl.class).readOneMedia(media.getUuid()))
                .withRel("read"),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(MediaPresenterImpl.class).downloadOneMedia(media.getUuid()))
                .withRel("download"),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(MediaPresenterImpl.class).updateOneMedia(media.getUuid(), null))
                .withRel("update"),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(MediaPresenterImpl.class).deleteOneMedia(media.getUuid()))
                .withRel("delete"));
    }
}
