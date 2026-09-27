package com.osamuharu.file.presentation.controllers;


import com.osamuharu.file.application.services.FileMetadataService;
import com.osamuharu.file.application.services.FileService;
import com.osamuharu.file.presentation.dto.response.FileDto;
import com.osamuharu.shared.annotation.ResponseMessage;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/{version}/files", version = "v1")
@RequiredArgsConstructor
public class FileController {

  private final FileService service;
  private final FileMetadataService metadataService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseMessage("Upload file successfully")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("isAuthenticated()")
  public FileDto upload(@Valid @RequestPart("file") @NotNull MultipartFile file) {
    return service.uploadFile(file);
  }

  @GetMapping("/public/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Get file metadata successfully")
  public FileDto getMetadata(@PathVariable String id) {
    return metadataService.findById(id);
  }
}
