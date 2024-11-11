package com.dodok.honeypot.application.receivepraise.controller;

import com.dodok.honeypot.domain.receivepraise.dto.req.SaveReceivePraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.SaveReceivePraiseService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receive-praise")
public class ReceivePraiseController {

    private final SaveReceivePraiseService saveReceivePraiseService;

    @PostMapping("")
    public ResponseEntity<SuccessResponse<?>> saveReceivePraise(@RequestBody SaveReceivePraiseReqDto req) {
        saveReceivePraiseService.execute(req);
        return SuccessResponse.ok(null);
    }
}
