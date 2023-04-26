package com.ssafy.novvel.episode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodeRegistDto {

 @NotNull
 private Long coverId;

 @NotNull
 private Long point;

 @NotNull
 private String statusType;

 private EpisodeContextDto contents;
}
