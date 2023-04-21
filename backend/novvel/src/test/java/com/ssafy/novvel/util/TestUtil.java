package com.ssafy.novvel.util;

import com.ssafy.novvel.member.entity.Member;
import java.util.Optional;

public class TestUtil {

    public static Member getMember() {
        return Member.builder()
            .id(1L)
            .email("email@naver.com")
            .nickname("nickname")
            .point(0L)
            .build();
    }

    public static Optional<Member> getGUESTMember() {
        return Optional.of(Member.builder()
            .id(1L)
            .email("guest@naver.com")
            .role("ROLE_GUEST")
            .sub("Kakao_2755574744")
            .build()
        );
    }

    public static Optional<Member> getUSERMember() {
        return Optional.of(Member.builder()
            .id(1L)
            .email("user@naver.com")
            .role("ROLE_USER")
            .sub("Kakao_2755574745")
            .nickname("user")
            .point(0L)
            .build()
        );
    }

}
