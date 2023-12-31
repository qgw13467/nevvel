package com.ssafy.novvel.episode.dto;

import com.ssafy.novvel.context.dto.ContextTouchsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeContextDto {
    /**
     *
     *   "title": "string",
     *   "contents": [
     *     {
     *       "idx": "integer",
     *       "context": "String"
     *       "isEvent": "bool",
     *       "event": [
     *         {
     *           "myAssetId": "integer",
     *           "type": "string"
     *         },
     *       ]
     *     },
     *   ]
     */
    private Long coverId;

    private Long wirterId;

    private String coverTitle;

    private String title;

    private Long episodeId;

    private Long point;

    private List<ContextTouchsDto> contents;

    private Long prevEpisodeId;

    private Long nextEpisodeId;
}
