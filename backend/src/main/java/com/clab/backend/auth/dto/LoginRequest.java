package com.clab.backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 요청")
public record LoginRequest(

        @Schema(description = "사용자 ID", example = "admin")
        String userId,

        @Schema(description = "비밀번호", example = "securePassword!")
        String userPw
) {
}