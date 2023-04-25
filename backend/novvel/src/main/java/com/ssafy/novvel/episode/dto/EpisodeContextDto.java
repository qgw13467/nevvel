package com.ssafy.novvel.episode.dto;

import java.util.List;

public class EpisodeContextDto {
    /**
     *   "contents": [
     *     {
     *       "idx": "integer",
     *       "context": "String"
     *       "isEvent": "bool",
     *       "event": [
     *         {
     *           "myAssetId": "integer",
     *           "type": "string",
     *           "effect": "string",
     *         },
     *       ]
     *     },
     *   ]
     */
    private Long idx;

    private String context;

    private Boolean isEvent;

    private List<EpisodeAffectInfo> event;
}
