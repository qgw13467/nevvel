package com.ssafy.novvel.cover.entity;

import com.ssafy.novvel.file.entity.Resource;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.util.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cover extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_id")
    @Setter
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @NotNull
    private Member member;

    @Setter
    @NotNull
    private String title;

    @Setter
    private String description;

    @Setter
    @NotNull
    private String status;

    @Setter
    private LocalDate publishDate;

    @Setter
    @NotNull
    private Long likes;

}
