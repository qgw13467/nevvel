package com.ssafy.novvel.asset.dto;

import com.ssafy.novvel.asset.entity.Asset;
import com.ssafy.novvel.asset.entity.AssetType;
import com.ssafy.novvel.asset.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AssetSearchDto {
    private Long id;
    private AssetType type;
    private String thumbnail;
    private Long price;
    private List<Tag> tags = new ArrayList<>();

    public AssetSearchDto(Asset asset) {
        this.id = asset.getId();
        this.type = asset.getType();
        this.thumbnail = (asset.getResource().getThumbnailUrl() == null) ? "" : asset.getResource().getThumbnailUrl();
        this.price = asset.getPoint();
    }

    public void addTags(Tag tag){
        tags.add(tag);
    }

}
