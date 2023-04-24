package com.ssafy.novvel.asset.controller;

import com.ssafy.novvel.asset.dto.AssetSearchDto;
import com.ssafy.novvel.asset.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {


    private final AssetService assetService;

    //todo 유저정보를 통해 구매한 에셋인지 보내는 코드 자겅
    @GetMapping
    public ResponseEntity<?> findAssetsByTags(@RequestParam("keyword") List<String> keywords,
                                              @AuthenticationPrincipal OidcUser oidcUser,
                                              Pageable pageable) {

        Slice<AssetSearchDto> result = assetService.searchAssetByTag(keywords, pageable);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
