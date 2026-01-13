package com.microservice.media.usecase.provider;

import lombok.AllArgsConstructor;

import java.util.List;

import com.microservice.media.domain.repository.ProviderRepository;
import com.microservice.media.domain.repository.base.Criteria;

@AllArgsConstructor
public class CountProvidersUseCase {
    private final ProviderRepository providerRepository;

    public Integer handle(List<Criteria> criterias) {
        return providerRepository.countBy(criterias);
    }
}
