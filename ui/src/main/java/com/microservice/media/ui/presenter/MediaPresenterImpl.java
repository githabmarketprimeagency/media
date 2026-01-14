package com.microservice.media.ui.presenter;

import com.microservice.media.ui.mapper.media.MediaPaginatedRepresentationMapper;
import com.microservice.media.ui.mapper.SearchCriteriaMapper;
import com.microservice.media.ui.mapper.media.CreateMediaMapper;
import com.microservice.media.ui.mapper.media.UpdateMediaMapper;
import com.microservice.media.ui.mapper.media.ImportMediaMapper;
import com.microservice.media.ui.mapper.media.MediaMapper;
import com.microservice.media.ui.mapper.MetadataMapper;
import com.microservice.media.ui.spec.api.MediaPresenter;
import com.microservice.media.ui.spec.model.CountMediasCriteriaDto;
import com.microservice.media.ui.spec.model.FindMediasCriteriaDto;
import com.microservice.media.ui.spec.model.MediaCreateRequestDto;
import com.microservice.media.ui.spec.model.MediaUpdateRequestDto;
import com.microservice.media.ui.spec.model.MediaImportRequestDto;
import com.microservice.media.ui.spec.model.MediaDto;
import com.microservice.media.ui.spec.model.MediaPaginatedRepresentationDto;
import com.microservice.media.ui.spec.model.MetadatasDto;
import com.microservice.media.domain.model.Media;
import com.microservice.media.domain.repository.base.Criteria;
import com.microservice.media.usecase.media.CountMediasUseCase;
import com.microservice.media.usecase.media.CreateOneMediaUseCase;
import com.microservice.media.usecase.media.DeleteOneMediaUseCase;
import com.microservice.media.usecase.media.DeleteOneMediaMetadatasUseCase;
import com.microservice.media.usecase.media.DownloadOneMediaUseCase;
import com.microservice.media.usecase.media.FindMediasUseCase;
import com.microservice.media.usecase.media.GetOneMediaUseCase;
import com.microservice.media.usecase.media.ImportOneMediaUseCase;
import com.microservice.media.usecase.media.ReadOneMediaUseCase;
import com.microservice.media.usecase.media.UpdateOneMediaUseCase;
import com.microservice.media.usecase.media.UpsertOneMediaMetadatasUseCase;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MediaPresenterImpl implements MediaPresenter {
    private final CountMediasUseCase countMediasUseCase;
    private final CreateOneMediaUseCase createOneMediaUseCase;
    private final DeleteOneMediaUseCase deleteOneMediaUseCase;
    private final DeleteOneMediaMetadatasUseCase deleteOneMediaMetadatasUseCase;
    private final DownloadOneMediaUseCase downloadOneMediaUseCase;
    private final FindMediasUseCase findMediasUseCase;
    private final GetOneMediaUseCase getOneMediaUseCase;
    private final ImportOneMediaUseCase importOneMediaUseCase;
    private final ReadOneMediaUseCase readOneMediaUseCase;
    private final UpdateOneMediaUseCase updateOneMediaUseCase;
    private final UpsertOneMediaMetadatasUseCase upsertOneMediaMetadatasUseCase;

    @Override
    public ResponseEntity<Integer> countMedias(CountMediasCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(countMediasUseCase.handle(criteriaList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MediaDto> getOneMedia(UUID uuid) {
        return new ResponseEntity<>(
                MediaMapper.INSTANCE.fromDomain(getOneMediaUseCase.handle(uuid)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MediaPaginatedRepresentationDto> findMedias(FindMediasCriteriaDto criteriaDto) {
        List<Criteria> criteriaList = SearchCriteriaMapper.INSTANCE.toCriteriaList(criteriaDto);
        return new ResponseEntity<>(
                MediaPaginatedRepresentationMapper.INSTANCE.fromDomain(findMediasUseCase.handle(
                    criteriaList,
                    criteriaDto.getPageNumber() != null ? criteriaDto.getPageNumber() : 0,
                    criteriaDto.getPageSize() != null && criteriaDto.getPageSize() > 0 ? criteriaDto.getPageSize() : 10,
                    criteriaDto.getOrderBy()
                )),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MediaDto> createOneMedia(String entityUuid, MultipartFile file,
            String providerUuid, String providerPath, String description, MetadatasDto metadatas) {
        try {
            Media media = new Media();
            media.setEntityUuid(entityUuid);
            media.setName(file.getOriginalFilename());
            media.setMimeType(file.getContentType());
            media.setSize((double) file.getSize());
            media.setProviderUuid(providerUuid);
            media.setProviderPath(providerPath);
            media.setDescription(description);

            if (metadatas != null) {
                media.setMetadatas(MetadataMapper.INSTANCE.toDomain(metadatas));
            }

            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.contains(".")) {
                media.setExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
            }

            Path targetPath = Paths.get(providerPath, fileName);
            Files.createDirectories(targetPath.getParent());
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            MediaDto mediaDto = MediaMapper.INSTANCE.fromDomain(
                createOneMediaUseCase.handle(media)
            );

            return new ResponseEntity<>(mediaDto, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public ResponseEntity<MediaDto> importOneMedia(MediaImportRequestDto requestDto) {
        Media media = ImportMediaMapper.INSTANCE.toDomain(requestDto);

        MediaDto mediaDto = MediaMapper.INSTANCE.fromDomain(
            importOneMediaUseCase.handle(media)
        );

        return new ResponseEntity<>(mediaDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteOneMedia(UUID uuid) {
        deleteOneMediaUseCase.handle(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<MediaDto> updateOneMedia(UUID uuid, MediaUpdateRequestDto requestDto) {
        Media media = UpdateMediaMapper.INSTANCE.toDomain(requestDto);

        MediaDto mediaDto = MediaMapper.INSTANCE.fromDomain(
            updateOneMediaUseCase.handle(uuid, media)
        );

        return new ResponseEntity<>(mediaDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Resource> readOneMedia(UUID uuid) {
        Resource resource = readOneMediaUseCase.handle(uuid);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> downloadOneMedia(UUID uuid) {
        Resource resource = downloadOneMediaUseCase.handle(uuid);

        String filename = resource.getFilename();
        if (filename == null) {
            filename = "download";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<Void> deleteOneMediaMetadatas(UUID uuid, MetadatasDto metadatasDto) {
        deleteOneMediaMetadatasUseCase.handle(uuid, MetadataMapper.INSTANCE.toDomain(metadatasDto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> upsertOneMediaMetadatas(UUID uuid, MetadatasDto metadatasDto) {
        upsertOneMediaMetadatasUseCase.handle(uuid, MetadataMapper.INSTANCE.toDomain(metadatasDto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
