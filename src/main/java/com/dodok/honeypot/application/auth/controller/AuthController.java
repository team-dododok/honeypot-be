package com.dodok.honeypot.application.auth.controller;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.req.SendMailReqDto;
import com.dodok.honeypot.domain.auth.dto.req.ReissueJwtTokenReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.dto.res.ReissueJwtTokenResDto;
import com.dodok.honeypot.domain.auth.service.CheckMailService;
import com.dodok.honeypot.domain.auth.service.KakaoSocialLoginService;
import com.dodok.honeypot.domain.auth.service.SendMailService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KakaoSocialLoginService kakaoSocialLoginService;
    private final SendMailService sendMailService;
    private final CheckMailService checkMailService;

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

    @PostMapping("/reissue")//accessToken 재발행
    public ResponseEntity<SuccessResponse<?>> reissue(@RequestBody ReissueJwtTokenReqDto reissueJwtTokenDto) {
        ReissueJwtTokenResDto resDto = kakaoSocialLoginService.reissue(reissueJwtTokenDto);
        return SuccessResponse.ok(resDto);
    }

    @PostMapping("/send-mail")
    public ResponseEntity<SuccessResponse<?>> sendMail(@RequestBody SendMailReqDto req) {
        sendMailService.execute(req);
        return SuccessResponse.created(null);
    }

    @GetMapping("/check-mail")
    public ResponseEntity<SuccessResponse<?>> checkMail(@RequestParam String receiverMail,
                                                        @RequestParam String verificationNumber) {
        checkMailService.execute(receiverMail, verificationNumber);
        return SuccessResponse.ok(null);

    }

}
