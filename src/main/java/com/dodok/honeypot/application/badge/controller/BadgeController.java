package com.dodok.honeypot.application.badge.controller;

import com.dodok.honeypot.domain.badge.dto.res.AllBadgeResDto;
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

    @GetMapping("")
    public ResponseEntity<SuccessResponse<?>> getBadgeInfo(@RequestParam(name="id") Long memberId){
        AllBadgeResDto res = getBadgeInfoService.execute(memberId);
        return SuccessResponse.ok(res);
    }

}
