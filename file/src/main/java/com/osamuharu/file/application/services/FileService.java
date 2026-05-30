package com.osamuharu.file.application.services;

import com.osamuharu.file.presentation.dto.response.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  FileDto uploadFile(MultipartFile file);
}
