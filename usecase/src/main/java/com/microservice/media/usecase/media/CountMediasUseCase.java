package com.microservice.media.usecase.media;

import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.base.Criteria;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CountMediasUseCase {
    private final MediaRepository mediaRepository;

    public Integer handle(List<Criteria> criterias) {
        return mediaRepository.countBy(criterias);
    }
}
