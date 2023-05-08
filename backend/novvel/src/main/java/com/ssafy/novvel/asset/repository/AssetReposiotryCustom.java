package com.ssafy.novvel.asset.repository;

import com.ssafy.novvel.asset.dto.AssetFilterDto;
import com.ssafy.novvel.asset.dto.AssetSearchDto;
import com.ssafy.novvel.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssetReposiotryCustom {

    Page<AssetSearchDto> searchAsset(AssetFilterDto assetFilterDto, Member member, Pageable pageable);
}