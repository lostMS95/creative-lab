package com.clab.backend.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileHandler {

    //private static final Logger logger = LoggerFactory.getLogger(FileHandler.class);
    private final Path fileStorageLocation;

    public FileHandler(@Value("${file.upload-directory}") String uploadDirectory) {
        this.fileStorageLocation = Paths.get(uploadDirectory)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("파일 저장 디렉토리를 생성할 수 없습니다.", ex);
        }
    }

    public String storeFile(MultipartFile file) {

        // 파일이 필수여야되는지 체크.
        /*if (file == null || file.isEmpty()) {
            throw new FileStorageException("업로드할 파일이 선택되지 않았습니다.");
        }*/

        // 파일명 중복 방지를 위한 UUID 생성
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(originalFilename);
        String fileName = UUID.randomUUID().toString() + fileExtension;

        try {
            // 파일명에 부적절한 문자가 있는지 확인
            if (fileName.contains("..")) {
                throw new FileStorageException("파일명에 부적절한 문자가 포함되어 있습니다: " + fileName);
            }

            // 파일 저장
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("파일 저장 중 오류가 발생했습니다: " + fileName, ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("파일을 찾을 수 없습니다: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다: " + fileName, ex);
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filename.substring(lastIndexOf);
    }


    /**
     *
     ********** 커스텀 예외 클래스 **********
     */
    public static class FileStorageException extends RuntimeException {
        public FileStorageException(String message) {
            super(message);
        }

        public FileStorageException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class FileNotFoundException extends RuntimeException {
        public FileNotFoundException(String message) {
            super(message);
        }

        public FileNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
