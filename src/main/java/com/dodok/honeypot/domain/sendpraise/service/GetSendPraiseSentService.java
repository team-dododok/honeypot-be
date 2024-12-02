package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.sendpraise.dto.res.GetSendPraiseSentResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.domain.sendpraise.mapper.SendPraiseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetSendPraiseSentService {
    private final SendPraiseHelper sendPraiseHelper;
    private final SendPraiseMapper sendPraiseMapper;
    public GetSendPraiseSentResDto execute(String praiseUuid) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidOrElseThrow(praiseUuid);
        return sendPraiseMapper.toGetSendPraiseSentResDto(sendPraise);
    }
}
