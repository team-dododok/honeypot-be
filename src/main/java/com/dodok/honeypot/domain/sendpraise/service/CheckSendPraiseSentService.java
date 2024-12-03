package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.sendpraise.dto.req.CheckSendPraiseSentReqDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CheckSendPraiseSentService {
    private final SendPraiseHelper sendPraiseHelper;
    public void execute(CheckSendPraiseSentReqDto req) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidOrElseThrow(req.praiseUuid());
        sendPraise.updateSendStatus(SendStatus.SUCCESS);
        log.info("[kakao-talk callback api] requestBody praiseUuid: " + req.praiseUuid());
        log.info("[kakao-talk callback api] requestBody CHAT_TYPE: " + req.CHAT_TYPE());
        log.info("[kakao-talk callback api] requestBody HASH_CHAT_ID: " + req.HASH_CHAT_ID());
    }
}
