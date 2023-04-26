package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.context.service.ContextService;
import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import com.ssafy.novvel.episode.dto.EpisodeRegistDto;
import com.ssafy.novvel.episode.entity.Episode;
import com.ssafy.novvel.context.entity.Context;
import com.ssafy.novvel.context.repository.ContextRepository;
import com.ssafy.novvel.episode.repository.EpisodeRepository;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.member.repository.MemberRepository;
import com.ssafy.novvel.util.token.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService{

    private final EpisodeRepository episodeRepository;
    private final ContextRepository episodeContextRepository;
    private final MemberRepository memberRepository;
    private final ContextService contextService;

    @Override
    public Long createEpisode(EpisodeRegistDto episodeRegistDto, CustomUserDetails customUserDetails) {
        Member member = memberRepository.findById(customUserDetails.getId()).orElseThrow();
        EpisodeContextDto contents = episodeRegistDto.getContents();
        String contextId = contextService.createContext(contents.getContents());
        return null;
    }

    public EpisodeContextDto getEpisodeInfo(Long episodeId, CustomUserDetails customUserDetails) {
        Episode episode = episodeRepository.findById(episodeId).orElseThrow(
//                () -> {
//                    new 없는 에피소드입니다 에러
//                }
        );
        Context episodeContext = episodeContextRepository.findById(episode.getContextId()).orElseThrow();
        return new EpisodeContextDto(episode.getId(), episodeContext.getContents());
    }

}
