package com.clab.backend.user.controller;

import com.clab.backend.user.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "회원 관련 API")
public interface UserControllerSpecification {

    @Operation(
            summary = "회원가입 API",
            description = "새로운 사용자를 등록합니다. <br>" +
                    "이 요청은 `RegisterRequest` JSON 객체를 필요로 하며, <br>" +
                    "유효하지 않은 데이터가 들어오면 적절한 오류 메시지를 반환합니다."
    )
    ResponseEntity<?> register(
            @Parameter(description = "회원가입 요청 객체", required = true)
            @RequestBody RegisterRequest registerRequest
    );
}