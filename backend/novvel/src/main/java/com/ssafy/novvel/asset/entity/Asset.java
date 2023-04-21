package com.ssafy.novvel.asset.entity;

import com.ssafy.novvel.file.entity.Resource;
import com.ssafy.novvel.util.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Asset extends BaseEntity {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private Resource resource;

    @Setter
    @NotNull
    private String type;

    @Setter
    private String description;

    @Setter
    @NotNull
    private Long point;

}
