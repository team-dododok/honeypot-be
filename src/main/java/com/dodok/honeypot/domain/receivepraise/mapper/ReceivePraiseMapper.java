package com.dodok.honeypot.domain.receivepraise.mapper;

import com.dodok.honeypot.domain.receivepraise.dto.res.GetReceivedPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import org.springframework.stereotype.Component;

@Component
public class ReceivePraiseMapper {

    public static GetReceivedPraiseResDto getReceivedPraiseResDto(SendPraise sendPraise, Long savedGroupId) {
        return GetReceivedPraiseResDto.of(sendPraise.getContent(), sendPraise.getSender().getName(), sendPraise.getReceiverName(),
                sendPraise.getGroup().getName(), savedGroupId, sendPraise.getHoneyStamp().getImageUrl());
    }
}
