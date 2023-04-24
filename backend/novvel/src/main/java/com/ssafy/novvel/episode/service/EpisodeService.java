package com.ssafy.novvel.episode.service;

import com.ssafy.novvel.cover.entity.Cover;
import com.ssafy.novvel.episode.entity.Episode;

import java.io.IOException;

public interface EpisodeService {
    Episode addEpisode(Cover cover) throws IOException;
}
