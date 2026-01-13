package com.microservice.media.infrastructure.mapper.hateoas.requestcheck;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.microservice.media.ui.presenter.RequestCheckPresenterImpl;
import com.microservice.media.infrastructure.model.RequestCheckEntity;

@Component("requestCheckModelAssembler")
public class RequestCheckModelAssembler
        implements RepresentationModelAssembler<RequestCheckEntity, EntityModel<RequestCheckEntity>> {

    @Override
    public EntityModel<RequestCheckEntity> toModel(RequestCheckEntity user) {
        return EntityModel.of(user,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RequestCheckPresenterImpl.class)
                .getOneRequestCheck(user.getUuid())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RequestCheckPresenterImpl.class).deleteOneRequestCheck(user.getUuid()))
                .withRel("delete"));
    }
}
