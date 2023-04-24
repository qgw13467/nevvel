package com.ssafy.novvel.asset.controller;

import com.ssafy.novvel.asset.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<?> findAssetsByTags(@RequestParam("keyword")List<String> keywords){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
