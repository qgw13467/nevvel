package com.ssafy.novvel.episode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockRegistDto {

    private Long episodeId;

    private Long idx;

    private String context;
}
