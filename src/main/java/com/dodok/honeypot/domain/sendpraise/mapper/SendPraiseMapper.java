package com.dodok.honeypot.domain.sendpraise.mapper;

import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import org.springframework.stereotype.Component;

@Component
public class SendPraiseMapper {
    public SendPraiseResDto toSendPraiseResDto(SendPraise sendPraise) {
        return SendPraiseResDto.of(sendPraise.getUuid());
    }
}

