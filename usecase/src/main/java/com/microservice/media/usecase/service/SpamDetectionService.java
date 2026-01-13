package com.microservice.media.usecase.service;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.repository.RequestCheckRepository;

@AllArgsConstructor
public class SpamDetectionService {
    public void detectSpam(RequestCheck requestCheck) {
        // empty because this is only for example purpose
    }
}
