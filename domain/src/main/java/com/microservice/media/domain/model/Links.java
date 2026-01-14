package com.microservice.media.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Links {
    private Link self;
    private Link read;
    private Link download;
    private Link update;
    private Link delete;

    @Getter
    @Setter
    public static class Link {
        private String href;

        public Link(String href) {
            this.href = href;
        }
    }
}
