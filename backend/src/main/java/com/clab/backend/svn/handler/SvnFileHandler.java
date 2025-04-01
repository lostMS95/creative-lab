package com.clab.backend.svn.handler;

import com.clab.backend.svn.dto.SvnUploadRequest;
import com.clab.backend.svn.service.FileService;
import com.clab.backend.svn.service.SvnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SvnFileHandler {

    private final FileService fileService;
    private final SvnService svnService;

    public void handleExcelUpload(SvnUploadRequest request, MultipartFile file) throws Exception {
        try {
            // 파일 저장
            String savedFilePath = fileService.saveFile(file);

            // SVN 업로드
            svnService.svnUpload(request, savedFilePath);

        } catch (Exception e) {
            throw new Exception("엑셀 업로드 중 오류 발생", e);
        }
    }
}