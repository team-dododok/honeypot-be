package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.req.MemberServiceConsentUpdateReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ServiceConsent;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ServiceConsentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMemberServiceConsentService {
    private final MemberHelper memberHelper;
    private final ServiceConsentHelper serviceConsentHelper;

    public void execute(Long memberId, MemberServiceConsentUpdateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        ServiceConsent serviceConsent = serviceConsentHelper.findByMemberIdOrElseThrow(member);
        serviceConsent.updateServiceConsent(requestDto.emailMarketing());
    }
}
