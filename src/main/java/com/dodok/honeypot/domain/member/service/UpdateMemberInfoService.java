package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.req.MemberUpdateReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMemberInfoService {
    private final MemberHelper memberHelper;

    public void execute(Long memberId, MemberUpdateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        member.updateMember(requestDto);
    }
}
