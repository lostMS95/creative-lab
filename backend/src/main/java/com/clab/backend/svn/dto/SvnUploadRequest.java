package com.clab.backend.svn.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "SVN 업로드 요청")
public record SvnUploadRequest(

        @Schema(description = "커밋 메시지", example = "엑셀 파일 업로드")
        String commitMessage,

        @Schema(description = "소속", example = "개발")
        String expPosition,

        @Schema(description = "지출 구분", example = "팀비")
        String expTp,

        @Schema(description = "일자", example = "2025-03-04")
        LocalDate expDt,

        @Schema(description = "이름", example = "홍길동")
        String expNm,

        @Schema(description = "내용", example = "팀커피")
        String expContents,

        @Schema(description = "결재 구분", example = "승인")
        String expPymTp,

        @Schema(description = "금액", example = "150000")
        int expAmt
        ) {
}
