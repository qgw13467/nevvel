package com.ssafy.novvel.cover.repository;

import com.ssafy.novvel.cover.dto.EpisodeInfoDto;
import com.ssafy.novvel.cover.entity.Cover;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoverRepository extends JpaRepository<Cover, Long> {

    @Query(
        "SELECT new com.ssafy.novvel.cover.dto.EpisodeInfoDto(e.id, e.point, e.viewCount, e.localDateTime, th.pointChangeType, (me.member.id IS NOT NULL)) "
            +
            "FROM Cover c " +
            "JOIN FETCH c.episodes e " +
            "LEFT JOIN MemberEpisode me ON me.episode.id = e.id AND me.member.id = :memberId " +
            "LEFT JOIN TransactionHistory th ON th.episode.id = e.id AND th.member.id = :memberId "
            +
            "WHERE c.id = :coverId"
    )
    List<EpisodeInfoDto> findEpisodesInfoDto(@Param("coverId") Long coverId,
        @Param("memberId") Long memberId);
}
