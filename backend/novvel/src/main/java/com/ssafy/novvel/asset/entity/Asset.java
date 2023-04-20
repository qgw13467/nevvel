package com.ssafy.novvel.asset.entity;

import com.ssafy.novvel.file.entity.Resource;
import com.ssafy.novvel.member.entity.Member;
import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Size;

@Entity
public class Asset {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Resource resource;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @Enumerated(EnumType.STRING)
    private AssetType type;
    @Size(min = 0, max = 500)
    private String description;
    @NegativeOrZero
    private Long point;








}

