package com.dodok.honeypot.application.stamp.controller;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.service.StampService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stamp")
public class StampController {

    private final StampService stampService;
    @GetMapping("")
    ResponseEntity<SuccessResponse<?>> getAllStamp() {
        AllStampResDto allStamp = stampService.getAllStamp();
        return SuccessResponse.ok(allStamp);
    }
}
