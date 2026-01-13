package com.microservice.media.infrastructure.mapper.hateoas.provider;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.microservice.media.ui.presenter.ProviderPresenterImpl;
import com.microservice.media.infrastructure.model.ProviderEntity;

@Component("providerModelAssembler")
public class ProviderModelAssembler
        implements RepresentationModelAssembler<ProviderEntity, EntityModel<ProviderEntity>> {

    @Override
    public EntityModel<ProviderEntity> toModel(ProviderEntity provider) {
        return EntityModel.of(provider,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProviderPresenterImpl.class)
                .getOneProvider(provider.getUuid())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProviderPresenterImpl.class).updateOneProvider(provider.getUuid(), null))
                .withRel("update"),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProviderPresenterImpl.class).deleteOneProvider(provider.getUuid()))
                .withRel("delete"));
    }
}
