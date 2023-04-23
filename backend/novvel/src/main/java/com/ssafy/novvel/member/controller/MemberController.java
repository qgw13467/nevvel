package com.ssafy.novvel.member.controller;

import com.ssafy.novvel.member.service.MemberService;
import com.ssafy.novvel.util.token.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping("/signout")
    public ResponseEntity<?> signOut(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        memberService.signOut(customUserDetails.getMember());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
