package com.exuberant.verifiableCredentialsApp.upload;

import com.exuberant.verifiableCredentialsApp.entity.UploadFile;
import com.exuberant.verifiableCredentialsApp.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/file-uploader")
public class FileUploadController {

    private final UploadFileRepository uploadFileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileContent(file.getBytes());
        uploadFile = uploadFileRepository.save(uploadFile);
        return ResponseEntity.ok(uploadFile.getId());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String fileId) {
        Optional<UploadFile> fileOptional = uploadFileRepository.findById(fileId);

        if (fileOptional.isPresent()) {
            UploadFile uploadFile = fileOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                            .filename(uploadFile.getFileName()).build());
            headers.setContentLength(uploadFile.getFileContent().length);

            return new ResponseEntity<>(uploadFile.getFileContent(), headers, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
