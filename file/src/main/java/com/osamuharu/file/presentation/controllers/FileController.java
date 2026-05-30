package com.osamuharu.file.presentation.controllers;


import com.osamuharu.file.application.services.FileMetadataService;
import com.osamuharu.file.application.services.FileService;
import com.osamuharu.file.presentation.dto.response.FileDto;
import com.osamuharu.shared.annotation.ResponseMessage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/{version}/files", version = "v1")
@RequiredArgsConstructor
public class FileController {

  private final FileService service;
  private final FileMetadataService metadataService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseMessage("Upload file successfully")
  public FileDto upload(@Valid @RequestParam("file") @NotNull MultipartFile file) {
    return service.uploadFile(file);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FileDto getMetadata(@PathVariable String id) {
    return metadataService.findById(id);
  }
}
