package com.dodok.honeypot.application.badge.controller;

import com.dodok.honeypot.domain.badge.dto.req.CompletedBadgeReqDto;
import com.dodok.honeypot.domain.badge.dto.res.CompletedBadgeResDto;
import com.dodok.honeypot.domain.badge.service.GetBadgeInfoService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/badge")
public class BadgeController {
    private final GetBadgeInfoService getBadgeInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getBadgeInfo(@PathVariable("id") CompletedBadgeReqDto req){
        CompletedBadgeResDto res = getBadgeInfoService.execute(req);
        return SuccessResponse.ok(res);
    }

}
