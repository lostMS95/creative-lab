package com.clab.backend.user.service;

import com.clab.backend.user.dto.RegisterRequest;
import com.clab.backend.user.domain.User;
import com.clab.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest request) {
        // 중복 검사
        if (userRepository.existsByUserId(request.userId())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다");
        }

        // 회원가입
        User user = new User();
        user.setUserId(request.userId());
        user.setUserNm(request.userNm());
        user.setUserPw(passwordEncoder.encode(request.userPw()));

        userRepository.save(user);
    }
}
