package com.ssafy.novvel.episode.controller;

import com.ssafy.novvel.episode.dto.EpisodeInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/episodes")
@RequiredArgsConstructor
public class EpisodeController {


    @PostMapping
    public String createEpisode() {

        return null;
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<EpisodeInfoDto> getEpisode(@PathVariable("episodeId") Long episodeId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{episodeId}")
    public String deleteEpisode() {

        return null;
    }

    @PutMapping("/{episodeId}")
    public String editEpisode() {

        return null;
    }
}
