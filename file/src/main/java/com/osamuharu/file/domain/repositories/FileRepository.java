package com.osamuharu.file.domain.repositories;

import com.osamuharu.file.domain.entities.File;

public interface FileRepository {

  File save(File user);

  File findById(String id);
}
