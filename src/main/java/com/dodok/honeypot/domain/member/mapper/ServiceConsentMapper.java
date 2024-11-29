package com.dodok.honeypot.domain.member.mapper;

import com.dodok.honeypot.domain.member.dto.res.GetMemberConsentResDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import org.springframework.stereotype.Component;

@Component
public class ServiceConsentMapper {
    public GetMemberConsentResDto toGetMemberConsentReqDto(Member member, ServiceConsent serviceConsent) {
        return GetMemberConsentResDto.of(member, serviceConsent);
    }
}
