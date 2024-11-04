package com.dodok.honeypot.application.auth.controller;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.service.KakaoSocialLoginService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KakaoSocialLoginService kakaoSocialLoginService;

    @GetMapping("/kakao-login")
    public ResponseEntity<SuccessResponse<?>> kakaoLogin(
            @RequestHeader("Authorization") String kakaoAccessToken,
            @RequestBody(required = false) KakaoLoginReqDto requestDto) {
        KakaoLoginResDto resDto = kakaoSocialLoginService.kakaoLogin(kakaoAccessToken, requestDto);
        return SuccessResponse.ok(resDto);
    }
}
