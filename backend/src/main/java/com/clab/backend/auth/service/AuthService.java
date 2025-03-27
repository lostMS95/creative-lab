package com.clab.backend.auth.service;

import com.clab.backend.auth.dto.LoginRequest;
import com.clab.backend.security.jwt.JwtTokenProvider;
import com.clab.backend.user.domain.User;
import com.clab.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public String login(LoginRequest loginRequest) {
        // 인증 수행 - 실패시 AuthenticationException 발생
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.userId(),
                        loginRequest.userPw()
                )
        );

        // 인증 성공시 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT 토큰 생성 및 반환
        // UserDetails에서 바로 정보를 가져올 수 있음
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUserId(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return jwtTokenProvider.createToken(user.getUserId(), user.getRole());
    }
}