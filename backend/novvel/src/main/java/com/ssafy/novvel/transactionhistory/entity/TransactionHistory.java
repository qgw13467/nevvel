package com.ssafy.novvel.transactionhistory.entity;

import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "user_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column(name = "type")
    private PointChangeType pointChangeType;

    @Setter
    private Long point;

}
