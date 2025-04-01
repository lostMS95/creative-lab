package com.clab.backend.util;

import com.clab.backend.user.domain.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;
import java.time.LocalDate;

@Component
public class SvnUtil {
    private final ExcelUtil excelUtil;
    private final String svnBaseUrl;
    private final String svnDirectory;

    public SvnUtil(ExcelUtil excelUtil,
                   @Value("${svn.base-url}") String svnBaseUrl,
                   @Value("${file.svn-checkout-directory}") String svnDirectory) {
        this.excelUtil = excelUtil;
        this.svnBaseUrl = svnBaseUrl;
        this.svnDirectory = svnDirectory;
    }

    public SVNClientManager createSVNClientManager(User user) throws Exception {
        String decryptedPassword = "수정필요!!!";
        return SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true),
                SVNWCUtil.createDefaultAuthenticationManager(user.getUserId(), decryptedPassword));
    }

    public String generateSVNUrl(String title) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        String quarterFolder = determineQuarterFolder(month);
        return svnBaseUrl + "/" + year + "년/개인지출영수증/" + quarterFolder + "/" + title;
    }

    private String determineQuarterFolder(int month) {
        if (month >= 1 && month <= 3) {
            return "2024년 1분기(01월-03월)";
        } else if (month >= 4 && month <= 6) {
            return "2024년 2분기(04월~06월)";
        } else if (month >= 7 && month <= 9) {
            return "2024년 3분기(07월~09월)";
        } else {
            return "2024년 4분기(10월~12월)";
        }
    }

    public SVNCommitInfo uploadFileToSVN(SVNClientManager clientManager, File file, String svnUrl, String commitMessage) throws Exception {
        return clientManager.getCommitClient().doImport(file, SVNURL.parseURIEncoded(svnUrl), commitMessage, null, true, true, SVNDepth.INFINITY);
    }

    public void checkoutAndModifyExcel(User user, String position, String expendType, String cardType, String excelDate, String excelDetail, String excelAmount) throws Exception {
        /*SVNClientManager clientManager = createSVNClientManager(user);

        // SVN 리포지토리에서 파일 체크아웃
        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        File checkoutDir = new File(svnDirectory);
        updateClient.doCheckout(SVNURL.parseURIEncoded(svnBaseUrl + "/2024년/경비지출내역"), checkoutDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);

        // 체크아웃한 엑셀 파일 경로 설정
        File excelFile = new File(svnDirectory + File.separator + "2024년 경비지출내역.xlsx");

        // 엑셀 파일 로드 및 수정
        Workbook workbook = excelUtil.loadExcelFile(excelFile);
        Object[] newRowData = {
                position,
                expendType,
                excelDate,
                user.getName(),
                excelDetail,
                cardType,
                cardType.equals("개인카드") ? "N" : "",
                Integer.parseInt(excelAmount)
        };
        excelUtil.addRowWithData(workbook.getSheetAt(0), newRowData);
        excelUtil.saveExcelFile(workbook, excelFile.getPath());

        // 변경된 파일을 SVN에 커밋
        commitChanges(clientManager, excelFile.getPath());*/
    }

    private void commitChanges(SVNClientManager clientManager, String filePath) throws Exception {
        File fileToCommit = new File(filePath);
        SVNCommitInfo commitInfo = clientManager.getCommitClient().doCommit(new File[]{fileToCommit}, false, "C-LAB에 의해 commit.", null, null, false, false, SVNDepth.INFINITY);

        // 커밋이 성공적으로 완료되었는지 확인
        if (commitInfo.getErrorMessage() == null) {
            if (fileToCommit.delete()) {
                System.out.println("파일이 성공적으로 삭제되었습니다: " + filePath);
            } else {
                System.out.println("파일 삭제에 실패했습니다: " + filePath);
            }
        } else {
            System.out.println("커밋에 실패했습니다: " + commitInfo.getErrorMessage().getFullMessage());
        }
    }
}
