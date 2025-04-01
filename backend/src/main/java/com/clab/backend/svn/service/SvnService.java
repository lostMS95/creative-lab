package com.clab.backend.svn.service;

import com.clab.backend.security.util.SecurityHelper;
import com.clab.backend.svn.dto.SvnUploadRequest;
import com.clab.backend.user.domain.User;
import com.clab.backend.user.repository.UserRepository;
import com.clab.backend.util.SvnUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SvnService {

    private final UserRepository userRepository;
    private final SvnUtil svnUtil;
    private final SecurityHelper securityHelper;

    public void svnUpload(SvnUploadRequest request, String filePath) throws Exception {
        // 인증된 사용자 가져오기
        User user = securityHelper.getAuthenticatedUser();

        // 파일 확장자 처리
        String fileExtension = getFileExtension(filePath);
        String titleWithExtension = request.title() + fileExtension;

        // SVN URL 생성 및 파일 업로드
        String svnUrl = svnUtil.generateSVNUrl(titleWithExtension);
        svnUtil.uploadFileToSVN(svnUtil.createSVNClientManager(user), new File(filePath), svnUrl, request.commitMessage());

        // 엑셀 체크아웃 및 수정
        String formattedDate = request.expDt().format(DateTimeFormatter.ofPattern("yyyy년 M월 d일 E요일", Locale.KOREAN));
        //svnUtil.checkoutAndModifyExcel(user, request);
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        return (lastDotIndex == -1) ? "" : filePath.substring(lastDotIndex);
    }
}
