package com.dodok.honeypot.domain.receivepraise.mapper;

import com.dodok.honeypot.domain.receivepraise.dto.res.GetReceivedPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.global.dto.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceivePraiseMapper {

    public static GetReceivedPraiseResDto getReceivedPraiseResDto(SendPraise sendPraise, Long savedGroupId) {
        return GetReceivedPraiseResDto.of(sendPraise.getContent(), sendPraise.getSender().getName(), sendPraise.getReceiverName(),
                sendPraise.getGroup().getName(), savedGroupId, sendPraise.getHoneyStamp().getImageUrl());
    }
    public GetGroupReceivePraiseResDto toGetGroupReceivePraiseResDto(List<ReceivePraiseInfo> receivePraiseInfos, PageInfo pageInfo) {
        return GetGroupReceivePraiseResDto.of(receivePraiseInfos, pageInfo);
    }
}
