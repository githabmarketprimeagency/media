package com.microservice.media.infrastructure.mapper.hateoas.requestcheck;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import com.microservice.media.infrastructure.model.RequestCheckEntity;

import lombok.AllArgsConstructor;

@Component("requestCheckAssembler")
@AllArgsConstructor
public class RequestCheckAssembler {

    private final RequestCheckModelAssembler requestCheckModelAssembler;
    private final PagedResourcesAssembler<RequestCheckEntity> pagedAssembler;

    public PagedModel<EntityModel<RequestCheckEntity>> toPagedModel(Page<RequestCheckEntity> requestChecks) {
        return pagedAssembler.toModel(requestChecks, requestCheckModelAssembler);
    }

    public EntityModel<RequestCheckEntity> toModel(RequestCheckEntity requestCheck) {
        return requestCheckModelAssembler.toModel(requestCheck);
    }
}
