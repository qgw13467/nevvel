package com.ssafy.novvel.episode.entity;

import com.ssafy.novvel.cover.entity.Cover;
import com.ssafy.novvel.transactionhistory.entity.PointChangeType;
import com.ssafy.novvel.util.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Episode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Cover cover;

    @Enumerated(EnumType.STRING)
    @Setter
    @NotNull
    private EpisodeStatusType status;

    @Setter
    @NotNull
    @PositiveOrZero
    private Long point;

    @Setter
    @NotNull
    @PositiveOrZero
    private Long viewCount;

    @Transient
    @Setter
    private EpisodeContext episodeContext;

    @Setter
    private String contextId;


}
