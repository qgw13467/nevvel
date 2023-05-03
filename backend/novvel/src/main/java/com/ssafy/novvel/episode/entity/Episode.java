package com.ssafy.novvel.episode.entity;

import com.ssafy.novvel.cover.entity.Cover;
import com.ssafy.novvel.util.BaseEntity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
    @PositiveOrZero
    private Long point;

    @Lob
    @NotNull
    private Byte[] contextId;

    @PositiveOrZero
    private Long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    Cover cover;
}
