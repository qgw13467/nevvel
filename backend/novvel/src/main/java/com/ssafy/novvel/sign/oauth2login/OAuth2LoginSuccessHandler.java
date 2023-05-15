package com.ssafy.novvel.sign.oauth2login;


import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.novvel.exception.NotFoundException;
import com.ssafy.novvel.member.dto.response.MemberInfoDto;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final ObjectMapper mapper;

    @Value("${redirect.login.profile}")
    private String profile;
    @Value("${redirect.login.index}")
    private String index;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        response.addCookie(oidcUser.getAttribute("accessToken"));
        response.addCookie(oidcUser.getAttribute("refreshToken"));

        String clientSub = "Kakao_" + oidcUser.getName();
        Member member = memberRepository.findBySub(clientSub).get();
        MemberInfoDto memberInfoDto = new MemberInfoDto(
                member.getId(),
                member.getPoint(),
                (member.getProfile() != null) ? member.getProfile().getUrl() : "",
                member.getNickname(),
                member.getDescription()

        );
        String memberInfoDtoString = URLEncoder.encode(mapper.writeValueAsString(memberInfoDto), "utf-8");
        log.info(memberInfoDto.toString());
        log.info(memberInfoDtoString);
        Cookie userDto = new Cookie("userDto", memberInfoDtoString);
//        userDto.setSecure(false);
//        userDto.setHttpOnly(false);
        userDto.setPath("/");
        response.addCookie(userDto);

        Cookie test = new Cookie("test", "test");
        response.addCookie(test);

        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_GUEST"))) {
            response.sendRedirect(profile);
        } else {
            response.sendRedirect(index);
        }
    }
}
