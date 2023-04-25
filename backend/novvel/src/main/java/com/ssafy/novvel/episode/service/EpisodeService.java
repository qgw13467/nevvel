package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.episode.dto.EpisodeInfoDto;

import java.io.IOException;

public interface EpisodeService {
    EpisodeInfoDto getEpisodeInfo(Long episodeId);
}
