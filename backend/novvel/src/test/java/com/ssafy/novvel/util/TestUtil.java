package com.ssafy.novvel.util;

import com.ssafy.novvel.asset.entity.Asset;
import com.ssafy.novvel.asset.entity.AssetType;
import com.ssafy.novvel.asset.entity.Tag;
import com.ssafy.novvel.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static Member getMember() {
        return Member.builder()
                .id(1L)
                .email("email@naver.com")
                .nickname("nickname")
                .point(0L)
                .build();
    }

    public static List<Tag> getTagList(int index) {
        List<Tag> result = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            Tag tag = new Tag(Long.valueOf(i), Long.valueOf(i).toString());
            result.add(tag);
        }
        return result;
    }

    public static List<Asset> getAssetList(int index) {
        List<Asset> result = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            Asset asset = Asset.builder()
                    .id(Long.valueOf(i))
                    .type(AssetType.IMAGE)
                    .description(String.valueOf(i))
                    .point(100L)
                    .build();
            result.add(asset);
        }
        return result;
    }


}
