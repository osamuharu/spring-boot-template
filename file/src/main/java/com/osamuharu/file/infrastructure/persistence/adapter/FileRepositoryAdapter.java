package com.osamuharu.file.infrastructure.persistence.adapter;

import com.osamuharu.file.domain.entities.File;
import com.osamuharu.file.domain.repositories.FileRepository;
import com.osamuharu.file.infrastructure.persistence.entities.FileEntity;
import com.osamuharu.file.infrastructure.persistence.mappers.FilePersistenceMapper;
import com.osamuharu.file.infrastructure.persistence.repositories.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepositoryAdapter implements FileRepository {

  private final FileJpaRepository jpaRepository;
  private final FilePersistenceMapper mapper;

  @Override
  public File save(File user) {
    FileEntity entity = mapper.toEntity(user);
    FileEntity savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public File findById(String id) {
    FileEntity entity = jpaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    return mapper.toDomain(entity);
  }
}
