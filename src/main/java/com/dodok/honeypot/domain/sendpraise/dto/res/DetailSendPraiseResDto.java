package com.dodok.honeypot.domain.sendpraise.dto.res;

import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)

public record DetailSendPraiseResDto(
        Long sendPraiseId,
        String senderProfileUrl,
        Long groupId,
        String groupName,
        String receiverName,
        LocalDateTime sendDate,
        String content,
        String badgeUrl
) {

    public static DetailSendPraiseResDto of(SendPraise sendPraise){
        return DetailSendPraiseResDto.builder()
                .sendPraiseId(sendPraise.getId())
                .senderProfileUrl(sendPraise.getSender().getImageUrl())
                .groupId(sendPraise.getGroup().getId())
                .groupName(sendPraise.getGroup().getName())
                .receiverName(sendPraise.getReceiverName())
                .sendDate(sendPraise.getCreatedAt())
                .content(sendPraise.getContent())
                .badgeUrl(sendPraise.getHoneyStamp().getImageUrl())
                .build();
    }

}
