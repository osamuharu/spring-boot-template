package com.osamuharu.file.application.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.osamuharu.file.application.mappers.FileAppMapper;
import com.osamuharu.file.application.services.FileService;
import com.osamuharu.file.application.useCases.CreateFileUseCase;
import com.osamuharu.file.domain.entities.File;
import com.osamuharu.file.infrastructure.cloudinary.CloudinaryResponse;
import com.osamuharu.file.presentation.dto.response.FileDto;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements FileService {

  private final Cloudinary cloudinary;
  private final CreateFileUseCase createFileUseCase;
  private final FileAppMapper mapper;

  @Value("${cloudinary.folder-name}")
  private String folderName;

  @Override
  @Validated
  public FileDto uploadFile(MultipartFile file) {
    try {
      Map options = ObjectUtils.asMap(
          "use_filename", true,
          "unique_filename", false,
          "overwrite", true
      );

      if (folderName != null && !folderName.isEmpty()) {
        options.put("folder", folderName);
      }

      Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
      CloudinaryResponse response = CloudinaryResponse
          .builder()
          .publicId(uploadResult.get("public_id").toString())
          .url(uploadResult.get("url").toString())
          .build();

      File createFile = createFileUseCase.execute(mapper.toDomain(response, file));

      return mapper.toDto(createFile);
    } catch (IOException e) {
      throw new RuntimeException("Upload failed", e);
    }
  }
}
