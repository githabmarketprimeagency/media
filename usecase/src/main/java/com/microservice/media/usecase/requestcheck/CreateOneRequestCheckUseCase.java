package com.microservice.media.usecase.requestcheck;

import com.microservice.media.domain.model.RequestCheck;
import com.microservice.media.domain.model.ResultCheck;
import com.microservice.media.domain.repository.RequestCheckRepository;
import com.microservice.media.usecase.service.SpamDetectionService;
import com.microservice.media.usecase.service.SpamStateService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOneRequestCheckUseCase {
    private final RequestCheckRepository requestCheckRepository;
    private final SpamStateService spamStateService;
    private final SpamDetectionService spamDetectionService;

    public ResultCheck handle(RequestCheck requestCheck) {
        if (spamStateService.isSpam(requestCheck)) {
            return new ResultCheck(true);
        }

        requestCheckRepository.save(requestCheck);
        spamDetectionService.detectSpam(requestCheck);

        return new ResultCheck(false);
    }
}
