package com.osamuharu.file.application.services;

import com.osamuharu.file.application.mappers.FileAppMapper;
import com.osamuharu.file.domain.repositories.FileRepository;
import com.osamuharu.file.presentation.dto.response.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileMetadataService {

  private final FileRepository repository;
  private final FileAppMapper mapper;

  public FileDto findById(String id) {
    return mapper.toDto(repository.findById(id));
  }
}
