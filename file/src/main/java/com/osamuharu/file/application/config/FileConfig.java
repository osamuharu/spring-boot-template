package com.osamuharu.file.application.config;

import com.osamuharu.file.application.useCases.CreateFileUseCase;
import com.osamuharu.file.domain.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FileConfig {

  private final FileRepository repository;

  @Bean
  public CreateFileUseCase createFileUseCase() {
    return new CreateFileUseCase(repository);
  }
}
