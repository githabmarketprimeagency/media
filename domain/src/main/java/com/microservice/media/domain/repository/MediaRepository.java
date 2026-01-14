package com.microservice.media.domain.repository;

import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.model.pagination.MediaPaginatedRepresentation;
import com.microservice.media.domain.repository.base.CrudPaginatedRepository;

public interface MediaRepository extends CrudPaginatedRepository<Media, MediaPaginatedRepresentation> {
}
