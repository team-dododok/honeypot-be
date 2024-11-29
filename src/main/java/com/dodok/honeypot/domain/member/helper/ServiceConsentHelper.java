package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.auth.dto.req.KakaoLoginReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import com.dodok.honeypot.domain.member.repository.ServiceConsentRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.dodok.honeypot.domain.member.entity.ServiceConsent.createServiceConsent;
import static com.dodok.honeypot.domain.member.error.ServiceConsentErrorCode.SERVICE_CONSENT_ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ServiceConsentHelper {
    private final ServiceConsentRepository serviceConsentRepository;

    public void createServiceConsentAndSave(KakaoLoginReqDto requestDto, Member member) {
        ServiceConsent serviceConsent = createServiceConsent(requestDto, member);
        serviceConsentRepository.save(serviceConsent);
    }

    public ServiceConsent findByMemberIdOrElseThrow(Member member) {
        return serviceConsentRepository.findByMember(member)
                .orElseThrow(() -> new EntityNotFoundException(SERVICE_CONSENT_ENTITY_NOT_FOUND));
    }
}
