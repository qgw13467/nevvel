package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import com.ssafy.novvel.episode.dto.EpisodeRegistDto;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.util.token.CustomUserDetails;

public interface EpisodeService {
    EpisodeContextDto getEpisodeInfo(Long episodeId, Member member);
    Long createEpisode(EpisodeRegistDto episodeRegistDto, Member member);
    String deleteEpisode(Long episodeId, Member member);
    String updateEpisode(Long episodeId, EpisodeRegistDto episodeRegistDto, Member member);
}
