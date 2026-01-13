package com.microservice.media.ui.presenter;

import com.microservice.media.ui.mapper.provider.ProviderPaginatedRepresentationMapper;
import com.microservice.media.ui.mapper.SearchCriteriaMapper;
import com.microservice.media.ui.mapper.provider.CreateProviderMapper;
import com.microservice.media.ui.mapper.provider.UpdateProviderMapper;
import com.microservice.media.ui.mapper.provider.ProviderMapper;
import com.microservice.media.ui.spec.api.ProviderPresenter;
import com.microservice.media.ui.spec.model.CountProvidersCriteriaDto;
import com.microservice.media.ui.spec.model.FindProvidersCriteriaDto;
import com.microservice.media.ui.spec.model.MetadatasDto;
import com.microservice.media.ui.spec.model.NativeProviderCreateRequestDto;
import com.microservice.media.ui.spec.model.SharePointProviderCreateRequestDto;
import com.microservice.media.ui.spec.model.NativeProviderUpdateRequestDto;
import com.microservice.media.ui.spec.model.SharePointProviderUpdateRequestDto;
import com.microservice.media.ui.spec.model.ProviderDto;
import com.microservice.media.ui.spec.model.ProviderPaginatedRepresentationDto;
import com.microservice.media.domain.model.Provider;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.usecase.provider.CountProvidersUseCase;
import com.microservice.media.usecase.provider.CreateOneProviderUseCase;
import com.microservice.media.usecase.provider.DeleteOneProviderUseCase;
import com.microservice.media.usecase.provider.FindProvidersUseCase;
import com.microservice.media.usecase.provider.GetOneProviderUseCase;
import com.microservice.media.usecase.provider.UpdateOneProviderUseCase;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProviderPresenterImpl implements ProviderPresenter {
    private final CountProvidersUseCase countProvidersUseCase;
    private final CreateOneProviderUseCase createOneProviderUseCase;
    private final DeleteOneProviderUseCase deleteOneProviderUseCase;
    private final FindProvidersUseCase findProvidersUseCase;
    private final GetOneProviderUseCase getOneProviderUseCase;
    private final UpdateOneProviderUseCase updateOneProviderUseCase;

    @Override
    public ResponseEntity<Integer> countProviders(CountProvidersCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(countProvidersUseCase.handle(criteriaList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderDto> getOneProvider(UUID uuid) {
        return new ResponseEntity<>(
                ProviderMapper.INSTANCE.fromDomain(getOneProviderUseCase.handle(uuid)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderPaginatedRepresentationDto> findProviders(FindProvidersCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(
                ProviderPaginatedRepresentationMapper.INSTANCE.fromDomain(findProvidersUseCase.handle(
                    criteriaList,
                    criteriaDto.getPageNumber() != null ? criteriaDto.getPageNumber() : 0,
                    criteriaDto.getPageSize() != null && criteriaDto.getPageSize() > 0 ? criteriaDto.getPageSize() : 10,
                    criteriaDto.getOrderBy()
                )),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderDto> createOneProvider(Object requestDto) {
        Provider provider;

        if (requestDto instanceof NativeProviderCreateRequestDto) {
            provider = CreateProviderMapper.INSTANCE.toDomainFromNative((NativeProviderCreateRequestDto) requestDto);
        } else if (requestDto instanceof SharePointProviderCreateRequestDto) {
            provider = CreateProviderMapper.INSTANCE.toDomainFromSharePoint((SharePointProviderCreateRequestDto) requestDto);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProviderDto providerDto = ProviderMapper.INSTANCE.fromDomain(
            createOneProviderUseCase.handle(provider)
        );

        return new ResponseEntity<>(providerDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteOneProvider(UUID uuid) {
        deleteOneProviderUseCase.handle(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProviderDto> updateOneProvider(UUID uuid, Object requestDto) {
        Provider provider;

        if (requestDto instanceof NativeProviderUpdateRequestDto) {
            provider = UpdateProviderMapper.INSTANCE.toDomainFromNative((NativeProviderUpdateRequestDto) requestDto);
        } else if (requestDto instanceof SharePointProviderUpdateRequestDto) {
            provider = UpdateProviderMapper.INSTANCE.toDomainFromSharePoint((SharePointProviderUpdateRequestDto) requestDto);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProviderDto providerDto = ProviderMapper.INSTANCE.fromDomain(
            updateOneProviderUseCase.handle(uuid, provider)
        );

        return new ResponseEntity<>(providerDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOneProviderMetadatas(UUID uuid, MetadatasDto metadatasDto) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> upsertOneProviderMetadatas(UUID uuid, MetadatasDto metadatasDto) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
