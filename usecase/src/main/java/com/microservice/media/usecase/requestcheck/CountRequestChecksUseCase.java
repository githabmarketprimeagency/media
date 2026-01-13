package com.microservice.media.usecase.requestcheck;

import lombok.AllArgsConstructor;

import java.util.List;

import com.microservice.media.domain.repository.RequestCheckRepository;
import com.microservice.media.domain.repository.base.Criteria;

@AllArgsConstructor
public class CountRequestChecksUseCase {
    private final RequestCheckRepository requestCheckRepository;

    public Integer handle(List<Criteria> criterias) {
        return requestCheckRepository.countBy(criterias);
    }
}
