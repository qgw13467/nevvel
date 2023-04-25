package com.ssafy.novvel.episode.repository;

import com.ssafy.novvel.episode.entity.EpisodeContext;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeContextRepository extends MongoRepository<EpisodeContext, String> {
}
