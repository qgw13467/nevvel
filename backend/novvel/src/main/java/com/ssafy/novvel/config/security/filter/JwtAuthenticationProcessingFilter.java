package com.ssafy.novvel.config.security.filter;

import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.member.repository.MemberRepository;
import com.ssafy.novvel.util.token.CustomUserDetails;
import com.ssafy.novvel.util.token.UserDtoUtils;
import com.ssafy.novvel.util.token.jwt.JWTProvider;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final UserDtoUtils userDtoUtils;
    public JwtAuthenticationProcessingFilter(JWTProvider jwtProvider,
        MemberRepository memberRepository, UserDtoUtils userDtoUtils) {
        this.jwtProvider = jwtProvider;
        this.memberRepository = memberRepository;
        this.userDtoUtils = userDtoUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull FilterChain filterChain) throws ServletException, IOException {

        log.info(request.getRequestURI());
        log.info(request.getMethod());

        String accessToken = null;
        String refreshToken = null;
        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(JWTProvider.getAccessToken())) {
                    accessToken = cookie.getValue();
                } else if (cookie.getName().equals(JWTProvider.getRefreshToken())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        if (accessToken != null) {
            try {
                String clientSub = jwtProvider.validateToken(accessToken);
                memberRepository.findBySub(clientSub).ifPresentOrElse(this::setAuthentication,
                    () -> response.setStatus(HttpStatus.UNAUTHORIZED.value()));
            } catch (ExpiredJwtException e) {
                if (refreshToken != null) {
                    checkReissueAccessToken(response, refreshToken);
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.addCookie(
                        jwtProvider.createEmptyCookie(JWTProvider.getAccessToken()));
                    response.addCookie(userDtoUtils.removeUserDtoCookie());
                }
            } catch (RuntimeException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                removeAllCookie(response);
            }
        } else {
            if(refreshToken != null) {
                checkReissueAccessToken(response, refreshToken);
            }
        }

        if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void checkReissueAccessToken(HttpServletResponse response, String refreshToken) {
        memberRepository.findByRefreshToken(refreshToken).ifPresentOrElse(member -> {
            if (this.checkRefreshToken(member.getRefreshToken())) {

                final Cookie accessToken = jwtProvider.createAccessToken(member.getSub());
                response.addCookie(accessToken);
                response.addCookie(userDtoUtils.createUserDtoCookie(member, accessToken.getMaxAge()));
            } else {

                removeAllCookie(response);
            }
        }, () -> removeAllCookie(response));
    }

    private void removeAllCookie(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.addCookie(
            jwtProvider.createEmptyCookie(JWTProvider.getAccessToken()));
        response.addCookie(
            jwtProvider.createEmptyCookie(JWTProvider.getRefreshToken()));
        response.addCookie(
            userDtoUtils.removeUserDtoCookie()
        );
    }

    private void setAuthentication(Member member) {

        UserDetails userDetailsUser = CustomUserDetails.builder()
            .member(member)
            .build();

        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                userDetailsUser.getAuthorities()));
    }

    private boolean checkRefreshToken(String refreshToken) {

        try {
            jwtProvider.validateToken(refreshToken);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}
