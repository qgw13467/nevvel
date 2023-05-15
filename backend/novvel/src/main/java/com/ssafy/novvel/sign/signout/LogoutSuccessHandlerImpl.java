package com.ssafy.novvel.sign.signout;

import com.ssafy.novvel.member.repository.MemberRepository;
import com.ssafy.novvel.util.token.CustomUserDetails;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.ssafy.novvel.util.token.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Value("${redirect.logout.index}")
    private String index;
    private final MemberRepository memberRepository;
    private final JWTProvider jwtProvider;



    @Override
    @Transactional
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        response.addCookie(jwtProvider.createEmptyCookie(JWTProvider.getAccessToken()));
        response.addCookie(jwtProvider.createEmptyCookie(JWTProvider.getRefreshToken()));

        Cookie userDto = new Cookie("userDto","");
        userDto.setMaxAge(0);
        userDto.setPath("/");
        response.addCookie(userDto);

        customUserDetails.getMember().removeToken();
        memberRepository.save(customUserDetails.getMember());
        response.sendRedirect(index);
    }
}
