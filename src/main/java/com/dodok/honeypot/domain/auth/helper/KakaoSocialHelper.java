package com.dodok.honeypot.domain.auth.helper;

import com.dodok.honeypot.domain.auth.repository.KakaoSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoSocialHelper {

    private final KakaoSocialRepository kakaoSocialRepository;
}
