package com.ssafy.novvel.episode.repository;

import com.ssafy.novvel.episode.entity.Episode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends MongoRepository<Episode, String> {
    Optional<Episode> findById(Long id);

}
