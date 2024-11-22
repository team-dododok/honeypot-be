package com.dodok.honeypot.domain.sendpraise.mapper;

import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.global.dto.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendPraiseMapper {
    public SendPraiseResDto toSendPraiseResDto(SendPraise sendPraise) {
        return SendPraiseResDto.of(sendPraise.getUuid());
    }

    public GetGroupSendPraiseResDto toGetGroupReceivePraiseResDto(List<SendPraiseInfo> sendPraiseInfos, PageInfo pageInfo) {
        return GetGroupSendPraiseResDto.of(sendPraiseInfos, pageInfo);
    }
}

