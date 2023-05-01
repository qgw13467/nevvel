package com.ssafy.novvel.transactionhistory.repository;

import com.ssafy.novvel.episode.entity.Episode;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.transactionhistory.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    Optional<TransactionHistory> findByMemberAndEpisode(Member member, Episode episode);

//    @Query("SELECT tme FROM TransactionHistory tme WHERE tme.member = :member  tme.episode in :ids)
//    List<TransactionHistory> findByMemberAndEpisodeIds(@Param("member") Member member, @Param("ids") List<Long> ids);
}
