package com.dodok.honeypot.global.controller;

import com.dodok.honeypot.global.auth.MemberId;
import com.dodok.honeypot.global.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController{


    @GetMapping
    public ResponseEntity<SuccessResponse<?>> healthCheck(@MemberId Long memberId){
        System.out.println("memberId = " + memberId);
        return SuccessResponse.ok("ʕ•ﻌ•ʔ <( Hi! )");
    }
}