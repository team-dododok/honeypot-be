package com.dodok.honeypot.domain.group.entity;

import com.dodok.honeypot.domain.group.dto.req.GroupUpdateReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.praise.entity.ReceivePraise;
import com.dodok.honeypot.domain.praise.entity.SendPraise;
import com.dodok.honeypot.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.dodok.honeypot.global.utils.UpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "tb_group")
@Entity
public class Group extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_idx", nullable = false)
    private Integer orderIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SendPraise> sendPraises = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReceivePraise> receivePraises = new ArrayList<>();

    public static Group createGroup(String name, Member member, Integer groupCount) {
        Group group = Group.builder()
                .name(name)
                .orderIdx(groupCount + 1)
                .member(member)
                .build();
        member.addGroup(group);
        return group;
    }

    public void updateGroup(GroupUpdateReqDto requestDto) {
        this.name = updateValue(this.name, requestDto.groupName());
    }
}
