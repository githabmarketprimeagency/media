package com.microservice.media.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;

@Configuration
public class HateoasConfig {

    @Bean
    public HateoasPageableHandlerMethodArgumentResolver customPaginationResolver() {
        HateoasPageableHandlerMethodArgumentResolver resolver
                = new HateoasPageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName("pageNumber");
        resolver.setSizeParameterName("pageSize");
        return resolver;
    }

    @Bean
    public PagedResourcesAssembler<?> pagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(customPaginationResolver(), null);
    }
}
