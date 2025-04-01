package com.clab.backend.auth.controller;

import com.clab.backend.auth.dto.LoginRequest;
import com.clab.backend.auth.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "인증 관련 API")
public interface AuthControllerSpecification {

    @Operation(
            summary = "로그인 API",
            description = "사용자가 로그인하면 JWT 토큰을 반환합니다. <br>" +
                    "이 요청은 `LoginRequest` JSON 객체를 필요로 하며, <br>" +
                    "유효하지 않은 데이터가 들어오면 적절한 오류 메시지를 반환합니다."
    )
    ResponseEntity<LoginResponse> login(
            @Parameter(description = "로그인 요청 객체", required = true)
            @RequestBody LoginRequest loginRequest
    );
}