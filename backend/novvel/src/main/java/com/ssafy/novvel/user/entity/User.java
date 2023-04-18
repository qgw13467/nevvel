package com.ssafy.novvel.user.entity;

import com.ssafy.novvel.file.entity.File;
import com.ssafy.novvel.util.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private File profile;

    @NotBlank
    @Size(min = 4, max = 20)
    private String nickname;

    @Email
    private String email;

    @PositiveOrZero
    private Long point;






}
