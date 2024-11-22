package com.dodok.honeypot.application.auth.controller;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.service.KakaoSocialLoginService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KakaoSocialLoginService kakaoSocialLoginService;

    @PostMapping("/kakao-login") //로그인
    public ResponseEntity<SuccessResponse<?>> kakaoLogin(
            @RequestHeader("Authorization") String kakaoAccessToken) {
        KakaoLoginResDto resDto = kakaoSocialLoginService.kakaoLogin(kakaoAccessToken);
        return SuccessResponse.ok(resDto);
    }

    @PostMapping("/register") //회원가입
    public ResponseEntity<SuccessResponse<?>> register(
            @RequestHeader("Authorization") String kakaoAccessToken,
            @RequestBody KakaoLoginReqDto requestDto) {
        KakaoLoginResDto resDto = kakaoSocialLoginService.register(kakaoAccessToken, requestDto);
        return SuccessResponse.ok(resDto);
    }
}
