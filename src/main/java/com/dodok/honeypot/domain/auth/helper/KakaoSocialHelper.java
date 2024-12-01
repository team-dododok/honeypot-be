package com.dodok.honeypot.domain.auth.helper;

import com.dodok.honeypot.domain.auth.entity.KakaoSocial;
import com.dodok.honeypot.domain.auth.repository.KakaoSocialRepository;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.dodok.honeypot.domain.auth.entity.KakaoSocial.createKakaoSocial;
import static com.dodok.honeypot.domain.auth.error.AuthErrorCode.KAKAO_INFO_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class KakaoSocialHelper {

    private final KakaoSocialRepository kakaoSocialRepository;

    public Long findKakaoSocialByKakaoAuthOrElseThrow(Long kakaoId) {
        Optional<KakaoSocial> kakaoSocial = kakaoSocialRepository.findByKakaoAuth(kakaoId);
        return kakaoSocial
                .orElseThrow(() -> new EntityNotFoundException(KAKAO_INFO_NOT_FOUND))
                .getMember().getId();
    }

    public void createKakaoSocialAndSave(Long kakaoAuth, Member member) {
        KakaoSocial kakaoSocial = createKakaoSocial(kakaoAuth, member);
        kakaoSocialRepository.save(kakaoSocial);
    }

    public Long findKakaoSocialByMemberOrElseThrow(Member member){
        return kakaoSocialRepository.findByMember(member)
                .orElseThrow(() -> new EntityNotFoundException(KAKAO_INFO_NOT_FOUND))
                .getKakaoAuth();
    }
}
