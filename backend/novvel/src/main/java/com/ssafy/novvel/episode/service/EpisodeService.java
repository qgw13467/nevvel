package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.episode.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
}
