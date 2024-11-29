package com.dodok.honeypot.domain.member.service;

import com.dodok.honeypot.domain.member.dto.res.GetMemberConsentResDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ServiceConsentHelper;
import com.dodok.honeypot.domain.member.mapper.ServiceConsentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMemberConsentService {
    private final ServiceConsentHelper serviceConsentHelper;
    private final ServiceConsentMapper serviceConsentMapper;
    private final MemberHelper memberHelper;


    public GetMemberConsentResDto execute(Long memberId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        ServiceConsent serviceConsent = serviceConsentHelper.findByMemberIdOrElseThrow(member);
        return serviceConsentMapper.toGetMemberConsentReqDto(member, serviceConsent);
    }
}
