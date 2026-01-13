package com.microservice.media.usecase.service;

import com.microservice.media.domain.model.RequestCheck;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpamStateService {

    public Boolean isSpam(RequestCheck requestCheck) {
        return isRequestSpam(requestCheck) || isUserSpam(requestCheck);
    }

    public Boolean isRequestSpam(RequestCheck requestCheck) {
        return true;
    }

    public Boolean isUserSpam(RequestCheck requestCheck) {
        return false;
    }
}
