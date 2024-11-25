package com.dodok.honeypot.application.auth.controller;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.auth.dto.req.ReissueJwtTokenReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLoginResDto;
import com.dodok.honeypot.domain.auth.dto.res.ReissueJwtTokenResDto;
import com.dodok.honeypot.domain.auth.service.KakaoSocialLoginService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import com.dodok.honeypot.global.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KakaoSocialLoginService kakaoSocialLoginService;
    private final MailUtils mailUtils;

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

    @PostMapping("/mail")
    public ResponseEntity<SuccessResponse<?>> sendMail(@RequestBody String mail) {
//        mailUtils.sendMail(mail);
        HashMap<String, Object> map = new HashMap<>();

        int number;

        try {
            number = mailUtils.sendMail(mail);
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        System.out.println("map = " + map);
        return null;

    }
}
