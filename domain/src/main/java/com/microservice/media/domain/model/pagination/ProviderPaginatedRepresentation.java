package com.microservice.media.domain.model.pagination;

import java.util.List;
import com.microservice.media.domain.model.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderPaginatedRepresentation extends PaginatedRepresentation<Provider> {

    public ProviderPaginatedRepresentation(List<Provider> content, int totalPages, int pageSize, int pageNumber,
            int totalElements, PageNavigationLinks pageNavigationlinks) {
        super(content, totalPages, pageSize, pageNumber, totalElements, pageNavigationlinks);
    }

}
