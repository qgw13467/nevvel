package com.ssafy.novvel.context.entity;

import com.ssafy.novvel.context.dto.ContextTouchsDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "contexts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Context {

    @Id
    private String id;

    @Setter
    private List<ContextTouchsDto> contents;

    public Context(List<ContextTouchsDto> contents) {
        this.contents = contents;
    }
}
