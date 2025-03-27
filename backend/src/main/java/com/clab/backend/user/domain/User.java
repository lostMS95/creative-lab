package com.clab.backend.user.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                            // 고유 사용자 ID (자동 증가)

    @Column(nullable = false, unique = true, length = 50)
    private String userId;                      // 사용자 아이디 (고유)

    @Column(nullable = false, length = 255)
    private String userPw;                      // 비밀번호 (암호화된 상태로 저장)

    @Column(nullable = false, length = 50)
    private String userNm;                      // 사용자 이름

    @Column(nullable = false)
    private Boolean isActive = true;            // 계정 활성화 여부

    @Column(nullable = false, length = 20)
    private String role = "USER";               // 역할 (e.g., USER, ADMIN)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 계정 생성 일시

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();  // 계정 정보 수정 일시

    private LocalDateTime lastLogin;                        // 마지막 로그인 일시
}