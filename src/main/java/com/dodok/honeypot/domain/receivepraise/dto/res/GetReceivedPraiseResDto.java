package com.dodok.honeypot.domain.receivepraise.dto.res;

import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetReceivedPraiseResDto(
String content,

String senderName,
String receiverName,
String groupName,
Long groupId,
SendStatus sendStatus,
String imageUrl
) {
    public static GetReceivedPraiseResDto of(
            String content, String senderName, String receiverName, String groupName, Long groupId, SendStatus sendStatus,String imageUrl) {
        return GetReceivedPraiseResDto.builder()
                .content(content)
                .senderName(senderName)
                .receiverName(receiverName)
                .groupName(groupName)
                .groupId(groupId)
                .sendStatus(sendStatus)
                .imageUrl(imageUrl)
                .build();
    }
}
