package com.clab.backend.security.util;

import com.clab.backend.user.domain.User;
import com.clab.backend.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 인증된 사용자 정보를 제공하는 유틸리티 클래스
 */
@Component
public class SecurityHelper {

    private final UserRepository userRepository;

    public SecurityHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 현재 인증된 사용자 정보를 조회합니다.
     *
     * @return 인증된 사용자 정보
     * @throws UsernameNotFoundException 인증 정보가 없거나 사용자를 찾을 수 없는 경우
     */
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return userRepository.findByUserId(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}