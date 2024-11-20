package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import com.dodok.honeypot.domain.member.repository.ServiceConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.dodok.honeypot.domain.member.entity.ServiceConsent.createServiceConsent;

@RequiredArgsConstructor
@Component
public class ServiceConsentHelper {
    private final ServiceConsentRepository serviceConsentRepository;

    public void createServiceConsentAndSave(KakaoLoginReqDto requestDto, Member member) {
        ServiceConsent serviceConsent = createServiceConsent(requestDto, member);
        serviceConsentRepository.save(serviceConsent);
    }
}
