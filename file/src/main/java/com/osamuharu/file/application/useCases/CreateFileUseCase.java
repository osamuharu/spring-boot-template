package com.osamuharu.file.application.useCases;

import com.osamuharu.file.domain.entities.File;
import com.osamuharu.file.domain.repositories.FileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateFileUseCase {

  private final FileRepository repository;

  public File execute(File file) {
    return repository.save(file);
  }
}
