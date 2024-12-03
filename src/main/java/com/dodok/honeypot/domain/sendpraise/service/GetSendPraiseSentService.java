package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.sendpraise.dto.res.GetSendPraiseSentResDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.domain.sendpraise.mapper.SendPraiseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GetSendPraiseSentService {
    private final SendPraiseHelper sendPraiseHelper;
    private final SendPraiseMapper sendPraiseMapper;
    public GetSendPraiseSentResDto execute(String praiseUuid) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidOrElseThrow(praiseUuid);
        sendPraise.updateSendStatus(SendStatus.SUCCESS);
        return sendPraiseMapper.toGetSendPraiseSentResDto(sendPraise);
    }
}
