package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import com.ssafy.novvel.episode.dto.EpisodeRegistDto;
import com.ssafy.novvel.util.token.CustomUserDetails;

public interface EpisodeService {
    EpisodeContextDto getEpisodeInfo(Long episodeId, CustomUserDetails customUserDetails);
    Long createEpisode(EpisodeRegistDto episodeRegistDto, CustomUserDetails customUserDetails);
}
