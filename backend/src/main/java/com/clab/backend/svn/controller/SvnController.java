package com.clab.backend.svn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
@Tag(name = "Example API", description = "예제 API 설명")
public class SvnController {
    @Operation(summary = "Hello 메시지 반환", description = "이 API는 간단한 Hello 메시지를 반환합니다.")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Swagger!";
    }
}
