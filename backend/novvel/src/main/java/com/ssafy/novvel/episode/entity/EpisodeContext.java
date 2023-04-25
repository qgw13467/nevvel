package com.ssafy.novvel.episode.entity;

import com.ssafy.novvel.episode.dto.EpisodeContextDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "contexts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EpisodeContext {
    @MongoId
    private ObjectId id;

    private List<EpisodeContextDto> content;
}
