package com.ssafy.novvel.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) {
        log.info(authentication.getPrincipal().toString());
        // TODO
        // 1) 사용자 액세스 토큰과 리프레시 토큰 생성
        // 2) response 쿠키에 액세스 토큰과 리프레시 토큰 담기
        if (authentication.getAuthorities().stream()
            .allMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_GUEST"))) {
            // TODO 기본 정보 입력 창으로 리다이렉트
        } else {
            // TODO 메인으로 리다이렉트
        }
    }
}
