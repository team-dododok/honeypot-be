package com.dodok.honeypot.application.sendpraise.controller;

import com.dodok.honeypot.domain.sendpraise.dto.req.SendPraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.service.SendPraiseService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/send-praise")
public class SendPraiseController {

    private final SendPraiseService sendPraiseService;
    @PostMapping("")
    public ResponseEntity<SuccessResponse<?>> createSendReceive(@RequestBody SendPraiseReqDto reqDto) {
        SendPraiseResDto sendPraise = sendPraiseService.createSendPraise(reqDto);
        return SuccessResponse.ok(sendPraise);
    }
}
