package com.ssafy.novvel.episode.controller;

import com.ssafy.novvel.episode.entity.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/episodes")
@RequiredArgsConstructor
public class EpisodeController {

    @PostMapping
    public String createEpisode() {

        return null;
    }

    @GetMapping("/{episodeID}")
    public ResponseEntity<?> getEpisode() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{episodeID}")
    public String deleteEpisode() {

        return null;
    }

    @PutMapping("/{episodeID}")
    public String editEpisode() {

        return null;
    }
}
