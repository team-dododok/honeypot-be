package com.dodok.honeypot.domain.member.dto.res;

import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetMemberConsentResDto(
        Long memberId,
        Boolean serviceTerm,
        Boolean personalInfo,
        Boolean emailMarketing
) {
    public static GetMemberConsentResDto of(Member member, ServiceConsent serviceConsent) {
        return GetMemberConsentResDto.builder()
                .memberId(member.getId())
                .serviceTerm(serviceConsent.getServiceTerm())
                .personalInfo(serviceConsent.getPersonalInfo())
                .emailMarketing(serviceConsent.getEmailMarketing())
                .build();
    }
}
