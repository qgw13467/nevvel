package com.ssafy.novvel.asset.dto;

import com.ssafy.novvel.asset.entity.AssetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssetFilterDto {
    private AssetType assettype;
    private List<String> tags;
    private SearchType searchtype;

}
