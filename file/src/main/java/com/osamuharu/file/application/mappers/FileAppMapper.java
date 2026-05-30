package com.osamuharu.file.application.mappers;

import com.osamuharu.file.domain.entities.File;
import com.osamuharu.file.infrastructure.cloudinary.CloudinaryResponse;
import com.osamuharu.file.presentation.dto.response.FileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileAppMapper {

  @Mapping(source = "file.originalFilename", target = "originalName")
  @Mapping(source = "file.size", target = "size")
  @Mapping(source = "file.contentType", target = "format")
  @Mapping(source = "response.url", target = "url")
  @Mapping(source = "response.publicId", target = "publicId")
  File toDomain(CloudinaryResponse response, MultipartFile file);

  @Mapping(source = "timestamp.createdAt", target = "createdAt")
  @Mapping(source = "timestamp.updatedAt", target = "updatedAt")
  FileDto toDto(File file);
}