package com.clab.backend.svn.service;

import com.clab.backend.svn.dto.SvnUploadRequest;
import com.clab.backend.user.domain.User;
import com.clab.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SvnService {

    /*private final UserRepository userRepository;
    private final SvnUtil svnUtil;

    public void svnUpload(SvnUploadRequest request, String filePath) throws Exception {
        // 인증된 사용자 가져오기
        User user = getAuthenticatedUser();

        // 파일 확장자 처리
        String fileExtension = getFileExtension(filePath);
        String titleWithExtension = title + fileExtension;

        // SVN URL 생성 및 파일 업로드
        String svnUrl = svnUtil.generateSVNUrl(titleWithExtension);
        svnUtil.uploadFileToSVN(svnUtil.createSVNClientManager(user), new File(filePath), svnUrl, description);

        // 엑셀 체크아웃 및 수정
        String formattedDate = excelDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일 E요일", Locale.KOREAN));
        svnUtil.checkoutAndModifyExcel(user, position, expendType, cardType, formattedDate, excelDetail, excelAmount);
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUserId(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        return (lastDotIndex == -1) ? "" : filePath.substring(lastDotIndex);
    }*/
}
