package com.clab.backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원가입 요청")
public record RegisterRequest(

        @Schema(description = "사용자 ID", example = "admin")
        String userId,

        @Schema(description = "비밀번호", example = "securePassword!")
        String userPw,

        @Schema(description = "사용자 이름", example = "홍길동")
        String userNm
    ) {
}