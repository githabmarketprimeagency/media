package com.microservice.media.ui.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.media.domain.repository.base.Criteria;

@Mapper
public interface SearchCriteriaMapper {

    SearchCriteriaMapper INSTANCE = Mappers.getMapper(SearchCriteriaMapper.class);

    default public <T> List<Criteria> toCriteriaList(T object) {

        List<Criteria> criteriaList = new ArrayList<>();
        Set<String> EXCLUDED_FIELDS = Set.of("pageNumber", "pageSize");

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value == null || EXCLUDED_FIELDS.contains(field.getName())) {
                    continue;
                }
                Criteria criteria = new Criteria();
                criteria.setName(field.getName());
                criteria.setValue(value);
                criteriaList.add(criteria);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error mapping DTO to criteria", e);
            }
        }
        return criteriaList;
    }
}
