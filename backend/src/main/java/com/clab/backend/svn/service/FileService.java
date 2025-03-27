package com.clab.backend.svn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-directory}")
    private String uploadDirectory;

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("업로드할 파일이 선택되지 않았습니다.");
        }

        Path dirPath = Paths.get(uploadDirectory);
        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        String filePath = uploadDirectory + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        return filePath;
    }
}
