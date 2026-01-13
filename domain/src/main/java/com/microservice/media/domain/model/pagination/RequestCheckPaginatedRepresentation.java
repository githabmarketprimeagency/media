package com.microservice.media.domain.model.pagination;

import java.util.List;
import com.microservice.media.domain.model.RequestCheck;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCheckPaginatedRepresentation extends PaginatedRepresentation<RequestCheck> {

    public RequestCheckPaginatedRepresentation(List<RequestCheck> content, int totalPages, int pageSize, int pageNumber,
            int totalElements, PageNavigationLinks pageNavigationlinks) {
        super(content, totalPages, pageSize, pageNumber, totalElements, pageNavigationlinks);
    }

}
