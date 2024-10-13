package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.res.MemberInfoResDto;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMemberInfoService {
    private final MemberHelper memberHelper;

    public MemberInfoResDto execute(Long memberId) {
        return memberHelper.findMemberByIdOrElseThrow(memberId);
    }
}
