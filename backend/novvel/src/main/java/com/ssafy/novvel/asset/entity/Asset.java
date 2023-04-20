package com.ssafy.novvel.asset.entity;

import com.ssafy.novvel.file.entity.Resource;
import com.ssafy.novvel.member.entity.Member;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Resource resource;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @Enumerated(EnumType.STRING)
    private AssetType type;
    @Size(min = 0, max = 500)
    private String description;
    @PositiveOrZero
    private Long point;


}

