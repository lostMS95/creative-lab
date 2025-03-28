package com.clab.backend.svn.controller;

import com.clab.backend.svn.dto.SvnUploadRequest;
import com.clab.backend.svn.handler.SvnFileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/svn")
public class SvnUploadController implements SvnUploadControllerSpecification {

    private final SvnFileHandler svnFileHandler;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody SvnUploadRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        svnFileHandler.handleExcelUpload(request, file);
        return ResponseEntity.ok().build();
    }
}
