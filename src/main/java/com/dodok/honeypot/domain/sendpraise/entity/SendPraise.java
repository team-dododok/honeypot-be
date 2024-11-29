package com.dodok.honeypot.domain.sendpraise.entity;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import com.dodok.honeypot.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_send_praise")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SendPraise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "send_praise_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "project_status", nullable = false)
    private Boolean projectStatus;

    @Column(name = "receiver_name",nullable = false)
    private String receiverName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "honey_stamp_id", nullable = false)
    private HoneyStamp honeyStamp;

    public static SendPraise createSendPraise(String title, String content, Boolean projectStatus,
                                       String receiverName, Member sender, Group group, HoneyStamp honeyStamp){
        return SendPraise.builder()
                .title(title)
                .content(content)
                .projectStatus(projectStatus)
                .receiverName(receiverName)
                .sender(sender)
                .group(group)
                .honeyStamp(honeyStamp)
                .uuid(UUID.randomUUID().toString())
                .build();
    }

    public void updateGroup(Group group) {
        this.group = group;
    }
}
