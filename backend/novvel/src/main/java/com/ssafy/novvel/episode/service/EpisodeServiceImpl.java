package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.context.service.ContextService;
import com.ssafy.novvel.cover.entity.Cover;
import com.ssafy.novvel.cover.repository.CoverRepository;
import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import com.ssafy.novvel.episode.dto.EpisodeRegistDto;
import com.ssafy.novvel.episode.entity.Episode;
import com.ssafy.novvel.context.entity.Context;
import com.ssafy.novvel.context.repository.ContextRepository;
import com.ssafy.novvel.episode.repository.EpisodeRepository;
import com.ssafy.novvel.exception.NotFoundException;
import com.ssafy.novvel.exception.UnMatchUserException;
import com.ssafy.novvel.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService{

    private final EpisodeRepository episodeRepository;
    private final ContextRepository contextRepository;
    private final CoverRepository coverRepository;
    private final ContextService contextService;

    @Override
    @Transactional
    public Long createEpisode(EpisodeRegistDto episodeRegistDto, Long memberId) {
        Cover cover = checkCoverOptional(coverRepository.findById(episodeRegistDto.getCoverId()));

        //현재 로그인 유저가 cover 작성자인지 확인
        if (!cover.getMember().getId().equals(memberId)){
            // 본인이 작성한 소설의 에피소드만 작성할 수 있습니다.
            throw new UnMatchUserException();
        }

        ObjectId contextId = contextService.createContext(episodeRegistDto.getContents());
        Episode episode = new Episode(cover, episodeRegistDto, contextId);
//        Episode episode = new Episode(episodeRegistDto, contextId);
        episode = episodeRepository.save(episode);
        return episode.getId();
    }

    public EpisodeContextDto getEpisodeInfo(Long episodeId, Long memberId) {
        Episode episode = checkEpisodeOptional(episodeRepository.findById(episodeId));

        // 결제 관련 활성화 시 현재 유저가 에피소드를 구매한 유저가 맞는지 확인하는 로직 필요

        // 에러 뜨면 MongoDB 연결 확인
        Context context = checkContextOptional(contextRepository.findById(episode.getContextId()));

        // 현재 유저가 작성자가 아닐 때만 조회수 1 올리기
        if (episode.getCover().getMember().getId().equals(memberId)) {
            episode.setViewCount(episode.getViewCount() + 1);
        }
        return new EpisodeContextDto(episode.getId(), context.getContents());
    }

    @Override
    public String deleteEpisode(Long episodeId, Long memberId) {
        Episode episode = checkEpisodeOptional(episodeRepository.findById(episodeId));

        // 현재 유저가 에피소드 작성자인지 확인하는 작업 필요
        if (!episode.getCover().getMember().getId().equals(memberId)) {
            throw new UnMatchUserException();
        }
        Context context = checkContextOptional(contextRepository.findById(episode.getContextId()));
        contextRepository.delete(context);
        episodeRepository.delete(episode);
        return "deleted";
    }

    @Override
    public String updateEpisode(Long episodeId, EpisodeRegistDto episodeRegistDto, Long memberId) {
        Episode episode = checkEpisodeOptional(episodeRepository.findById(episodeId));
        Cover cover = checkCoverOptional(coverRepository.findById(episodeRegistDto.getCoverId()));
        ObjectId contextId = episode.getContextId();

//        커버 작성자와 현재 작성자 같은지 확인
        if (!cover.getMember().getId().equals(memberId)) {
            throw new UnMatchUserException();
        }
        episode.setPoint(episodeRegistDto.getPoint());
        episode.setStatusType(episodeRegistDto.getStatusType());

        // 에러 뜨면 MongoDB 연결 확인
        Context context = checkContextOptional(contextRepository.findById(contextId));
        context.setContents(episodeRegistDto.getContents());

        episodeRepository.save(episode);
        contextRepository.save(context);
        return "updated";
    }

    // Episode 없을 때 예외처리
    private Episode checkEpisodeOptional(Optional<Episode> episode) {
        return episode.orElseThrow(() -> {
            throw new NotFoundException("Episode가 없습니다.");
        });
    }

    // Cover 없을 때 예외처리
    private Cover checkCoverOptional(Optional<Cover> cover) {
        return cover.orElseThrow(() -> {
            throw new NotFoundException("시리즈가 없습니다.");
        });
    }

    // Context 없을 때 예외처리
    private Context checkContextOptional(Optional<Context> context) {
        return context.orElseThrow(() -> {
            throw new NotFoundException("Context가 없습니다(MongoDB 로직 수정 필요).");
        });
    }
}

