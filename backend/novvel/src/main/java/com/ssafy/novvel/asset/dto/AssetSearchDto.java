package com.ssafy.novvel.asset.dto;

import com.ssafy.novvel.asset.entity.Asset;
import com.ssafy.novvel.asset.entity.AssetTag;
import com.ssafy.novvel.asset.entity.AssetType;
import com.ssafy.novvel.asset.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AssetSearchDto {
    private Long id;
    private AssetType type;
    private String thumbnail;
    private Long price;
    private List<Tag> tags;

    public AssetSearchDto(Asset asset, List<Tag> assetTags) {
        this.id = asset.getId();
        this.type = asset.getType();
        this.thumbnail = (asset.getResource().getThumbnailUrl() == null) ? "" : asset.getResource().getThumbnailUrl();
        this.price = asset.getPoint();
        this.tags = (assetTags == null) ? null : assetTags;
    }

}
