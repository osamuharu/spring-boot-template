package com.osamuharu.file.infrastructure.persistence.mappers;


import com.osamuharu.file.domain.entities.File;
import com.osamuharu.file.infrastructure.persistence.entities.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilePersistenceMapper {

  File toDomain(FileEntity fileEntity);

  FileEntity toEntity(File user);
}

