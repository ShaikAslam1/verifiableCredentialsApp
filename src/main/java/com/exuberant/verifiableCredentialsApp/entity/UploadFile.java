package com.exuberant.verifiableCredentialsApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "upload_file")
public class UploadFile {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Lob
    @Column(name = "file_content", nullable = false)
    private byte[] fileContent;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
