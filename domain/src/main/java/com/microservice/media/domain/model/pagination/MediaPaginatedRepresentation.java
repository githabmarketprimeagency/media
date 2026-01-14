package com.microservice.media.domain.model.pagination;

import java.util.List;
import com.microservice.media.domain.model.Media;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaPaginatedRepresentation extends PaginatedRepresentation<Media> {

    public MediaPaginatedRepresentation(List<Media> content, int totalPages, int pageSize, int pageNumber,
            int totalElements, PageNavigationLinks pageNavigationlinks) {
        super(content, totalPages, pageSize, pageNumber, totalElements, pageNavigationlinks);
    }

}
