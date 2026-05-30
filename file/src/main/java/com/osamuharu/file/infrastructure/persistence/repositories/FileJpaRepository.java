package com.osamuharu.file.infrastructure.persistence.repositories;

import com.osamuharu.file.infrastructure.persistence.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<FileEntity, String> {

}
