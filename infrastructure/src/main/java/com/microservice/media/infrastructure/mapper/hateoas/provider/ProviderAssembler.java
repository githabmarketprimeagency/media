package com.microservice.media.infrastructure.mapper.hateoas.provider;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import com.microservice.media.infrastructure.model.ProviderEntity;

import lombok.AllArgsConstructor;

@Component("providerAssembler")
@AllArgsConstructor
public class ProviderAssembler {

    private final ProviderModelAssembler providerModelAssembler;
    private final PagedResourcesAssembler<ProviderEntity> pagedAssembler;

    public PagedModel<EntityModel<ProviderEntity>> toPagedModel(Page<ProviderEntity> providers) {
        return pagedAssembler.toModel(providers, providerModelAssembler);
    }

    public EntityModel<ProviderEntity> toModel(ProviderEntity provider) {
        return providerModelAssembler.toModel(provider);
    }
}
