package com.exuberant.verifiableCredentialsApp.repository;

import com.exuberant.verifiableCredentialsApp.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, String> {
}
