package com.ssafy.novvel.asset.entity;

import javax.persistence.*;

@Entity
public class AssetTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;
}
