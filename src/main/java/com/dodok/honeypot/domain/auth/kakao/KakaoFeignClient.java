package com.dodok.honeypot.domain.auth.kakao;

import com.dodok.honeypot.domain.auth.dto.req.KakaoServerLogOutReqDto;
import com.dodok.honeypot.domain.auth.dto.res.KakaoLogOutResDto;
import com.dodok.honeypot.domain.auth.dto.res.UserInfoFromKakaoResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao-feign-client", url = "https://kapi.kakao.com/")
public interface KakaoFeignClient {

    @GetMapping("v2/user/me")
    UserInfoFromKakaoResDto getKakaoUserInfo(@RequestHeader("Authorization") String kakaoAccessToken);

    @PostMapping("v1/user/logout")
    KakaoLogOutResDto logOut(@RequestHeader("Authorization") String serviceAppAdminKey,
                             @RequestBody KakaoServerLogOutReqDto dto);

}
