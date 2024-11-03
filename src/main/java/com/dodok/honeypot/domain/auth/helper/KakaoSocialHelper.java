package com.dodok.honeypot.domain.auth.helper;

import com.dodok.honeypot.domain.auth.entity.KakaoSocial;
import com.dodok.honeypot.domain.auth.repository.KakaoSocialRepository;
import com.dodok.honeypot.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.dodok.honeypot.domain.auth.entity.KakaoSocial.createKakaoSocial;

@RequiredArgsConstructor
@Component
public class KakaoSocialHelper {

    private final KakaoSocialRepository kakaoSocialRepository;

    public Optional<KakaoSocial> findKakaoSocialByKakaoAuth(Long kakaoId) {
        return kakaoSocialRepository.findByKakaoAuth(kakaoId);
    }

    public void createKakaoSocialAndSave(Long kakaoAuth, Member member){
        KakaoSocial kakaoSocial = createKakaoSocial(kakaoAuth, member);
        kakaoSocialRepository.save(kakaoSocial);
    }
}
