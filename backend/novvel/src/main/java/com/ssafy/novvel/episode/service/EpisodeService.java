package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import com.ssafy.novvel.episode.dto.EpisodeIdsDto;
import com.ssafy.novvel.episode.dto.EpisodePerchasing;
import com.ssafy.novvel.episode.dto.EpisodeRegistDto;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.util.token.CustomUserDetails;

import java.util.List;

public interface EpisodeService {
    EpisodeContextDto getEpisodeInfo(Long episodeId, Member member) ;
    Long createEpisode(EpisodeRegistDto episodeRegistDto, Member member);
    void deleteEpisode(Long episodeId, Long memberId);
    void updateEpisode(Long episodeId, EpisodeRegistDto episodeRegistDto, Member member);
    Integer perchaseEpisode(EpisodePerchasing episodePerchasing, Member member);
}
