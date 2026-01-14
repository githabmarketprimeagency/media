package com.microservice.media.usecase.media;

import com.microservice.media.domain.model.pagination.MediaPaginatedRepresentation;
import com.microservice.media.domain.repository.MediaRepository;
import com.microservice.media.domain.repository.base.Criteria;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindMediasUseCase {
    private final MediaRepository mediaRepository;

    public MediaPaginatedRepresentation handle(List<Criteria> criterias, Integer pageNumber, Integer pageSize, String orderBy) {
        return mediaRepository.findBy(criterias, pageNumber, pageSize, orderBy);
    }
}
