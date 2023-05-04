package com.ssafy.novvel.cover.controller;

import com.ssafy.novvel.cover.dto.CoverInfoAndEpisodesDto;
import com.ssafy.novvel.cover.dto.CoverRegisterDto;
import com.ssafy.novvel.cover.service.CoverService;
import com.ssafy.novvel.util.token.CustomUserDetails;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/covers")
public class CoverController {

    private final CoverService coverService;

    public CoverController(CoverService coverService) {
        this.coverService = coverService;
    }

    @PostMapping()
    public ResponseEntity<?> registerCover(@RequestPart(value = "file") MultipartFile file,
        @RequestPart(value = "coverRegisterDto") CoverRegisterDto coverRegisterDto,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) throws IOException {

        coverService.registerCover(file, coverRegisterDto, customUserDetails.getMember());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{cover-num}")
    public ResponseEntity<CoverInfoAndEpisodesDto> getAllEpisodes(@PathVariable("cover-num") Long coverNum,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return new ResponseEntity<>(
            coverService.getAllEpisodes(coverNum, customUserDetails.getId()), HttpStatus.OK);

    }
}
