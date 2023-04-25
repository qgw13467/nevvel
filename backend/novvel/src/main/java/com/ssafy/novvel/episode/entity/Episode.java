package com.ssafy.novvel.episode.entity;

import com.ssafy.novvel.cover.entity.Cover;
import com.ssafy.novvel.util.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

//@Entity
@Document(collection = "episode")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Episode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Cover cover;

    @Setter
    @NotNull
    @PositiveOrZero
    private Long point;

    @Setter
    @NotNull
    @PositiveOrZero
    private Long viewCount;
}
