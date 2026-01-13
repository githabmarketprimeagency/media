package com.microservice.media.ui.presenter;

import com.microservice.media.ui.mapper.requestcheck.RequestCheckPaginatedRepresentationMapper;
import com.microservice.media.ui.mapper.requestcheck.ResultCheckMapper;
import com.microservice.media.ui.mapper.SearchCriteriaMapper;
import com.microservice.media.ui.mapper.requestcheck.CreateRequestCheckMapper;
import com.microservice.media.ui.mapper.requestcheck.RequestCheckMapper;
import com.microservice.media.ui.spec.api.RequestCheckPresenter;
import com.microservice.media.ui.spec.model.CountRequestChecksCriteriaDto;
import com.microservice.media.ui.spec.model.FindRequestChecksCriteriaDto;
import com.microservice.media.ui.spec.model.RequestCheckCreateRequestDto;
import com.microservice.media.ui.spec.model.RequestCheckDto;
import com.microservice.media.ui.spec.model.RequestCheckPaginatedRepresentationDto;
import com.microservice.media.ui.spec.model.ResultCheckDto;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.usecase.requestcheck.CountRequestChecksUseCase;
import com.microservice.media.usecase.requestcheck.CreateOneRequestCheckUseCase;
import com.microservice.media.usecase.requestcheck.DeleteOneRequestCheckUseCase;
import com.microservice.media.usecase.requestcheck.FindRequestChecksUseCase;
import com.microservice.media.usecase.requestcheck.GetOneRequestCheckUseCase;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RequestCheckPresenterImpl implements RequestCheckPresenter {
    private final CountRequestChecksUseCase countRequestChecksUseCase;
    private final CreateOneRequestCheckUseCase createOneRequestCheckUseCase;
    private final DeleteOneRequestCheckUseCase deleteOneRequestCheckUseCase;
    private final FindRequestChecksUseCase findRequestChecksUseCase;
    private final GetOneRequestCheckUseCase getOneRequestCheckUseCase;

    @Override
    public ResponseEntity<Integer> countRequestChecks(CountRequestChecksCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(countRequestChecksUseCase.handle(criteriaList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RequestCheckDto> getOneRequestCheck(UUID uuid) {
        return new ResponseEntity<>(
                RequestCheckMapper.INSTANCE.fromDomain(getOneRequestCheckUseCase.handle(uuid)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RequestCheckPaginatedRepresentationDto> findRequestChecks(FindRequestChecksCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(
                RequestCheckPaginatedRepresentationMapper.INSTANCE.fromDomain(findRequestChecksUseCase.handle(
                    criteriaList,
                    criteriaDto.getPageNumber() != null ? criteriaDto.getPageNumber() : 0,
                    criteriaDto.getPageSize() != null && criteriaDto.getPageSize() > 0 ? criteriaDto.getPageSize() : 10,
                    criteriaDto.getOrderBy()
                )),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResultCheckDto> createOneRequestCheck(
            RequestCheckCreateRequestDto requestCheckCreateRequestDto) {
        ResultCheckDto resultCheck = ResultCheckMapper.INSTANCE.fromDomain(
            createOneRequestCheckUseCase.handle(
                CreateRequestCheckMapper.INSTANCE.toDomain(requestCheckCreateRequestDto)
            )
        );
        // disableExpiredRequestSpamsUseCase.handle();
        // disableExpiredUserSpamsUseCase.handle();
        return new ResponseEntity<>(resultCheck, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteOneRequestCheck(UUID uuid) {
        deleteOneRequestCheckUseCase.handle(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
