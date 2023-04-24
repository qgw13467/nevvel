package com.ssafy.novvel.episode.dto;

import com.ssafy.novvel.cover.entity.Cover;
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
 private Cover cover;

 @NotNull
 private Long point;
}
