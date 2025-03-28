package com.clab.backend.svn.controller;

import com.clab.backend.svn.dto.SvnUploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam; // Added this import
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Svn", description = "SVN 파일 업로드 API")
public interface SvnUploadControllerSpecification {

    @Operation(
            summary = "SVN 엑셀 업로드",
            description = "SVN 관련 데이터를 포함하는 엑셀 파일을 업로드합니다. <br>" +
                    "이 요청은 `SvnUploadRequest` JSON 객체와 파일을 함께 받아 처리합니다."
    )
    ResponseEntity<?> upload(
            @Parameter(description = "SVN 업로드 요청 객체", required = true)
            @RequestBody SvnUploadRequest request,

            @Parameter(description = "업로드할 엑셀 파일", required = true)
            @RequestParam("file") MultipartFile file
    ) throws Exception;
}