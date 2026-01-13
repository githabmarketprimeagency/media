package com.microservice.media.domain.repository.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    private String name;
    private Object value;
    private String compareason;
}
