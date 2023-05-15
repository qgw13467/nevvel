package com.ssafy.novvel.member.dto.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.ssafy.novvel.member.entity.Member;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@ToString
public class MemberInfoDto {
    private Long id;
    @PositiveOrZero
    private Long point;
    @NotNull
    private String profileImage;
    @NotNull
    private String nickname;
    @Size(max = 500)
    private String description;

    public MemberInfoDto(Long point, String profileImage, String nickname, String description) {
        this.point = point;
        this.profileImage = (profileImage != null) ? profileImage : "";
        this.nickname = (nickname != null) ? nickname : "";
        this.description = (description != null) ? description : "";
    }
    public MemberInfoDto(Long id, Long point, String profileImage, String nickname, String description) {
        this.id = id;
        this.point = point;
        this.profileImage = (profileImage != null) ? profileImage : "";
        this.nickname = (nickname != null) ? nickname : "";
        this.description = (description != null) ? description : "";
    }
}
