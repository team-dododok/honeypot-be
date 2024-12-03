package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.sendpraise.dto.req.CheckSendPraiseSentReqDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckSendPraiseSentService {
    private final SendPraiseHelper sendPraiseHelper;
    public void execute(CheckSendPraiseSentReqDto req) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidOrElseThrow(req.praiseUuid());
        sendPraise.updateSendStatus(SendStatus.SUCCESS);
        log.info("praise Uuid : "+req.praiseUuid() + "is Successfully Sent");
        log.info("requestBody : " + req);
    }
}
