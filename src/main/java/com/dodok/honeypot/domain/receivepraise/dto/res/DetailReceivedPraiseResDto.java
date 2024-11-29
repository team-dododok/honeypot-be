package com.dodok.honeypot.domain.receivepraise.dto.res;

import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder(access = AccessLevel.PRIVATE)
public record DetailReceivedPraiseResDto(
    Long receivePraiseId,
    String receiverProfileUrl,
    Long groupId,
    String groupName,
    String senderName,
    LocalDateTime receivedDate,
    String content,
    String badgeUrl
) {
    public static DetailReceivedPraiseResDto of(ReceivePraise receivePraise){
        return DetailReceivedPraiseResDto.builder()
                .receivePraiseId(receivePraise.getId())
                .receiverProfileUrl(receivePraise.getReceiver().getImageUrl())
                .groupId(receivePraise.getId())
                .groupName(receivePraise.getGroup().getName())
                .senderName(receivePraise.getSendPraise().getSender().getName())
                .receivedDate(receivePraise.getCreatedAt())
                .content(receivePraise.getSendPraise().getContent())
                .badgeUrl(receivePraise.getSendPraise().getHoneyStamp().getImageUrl())
                .build();
    }
}
