package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.dto.res.MemberInfoResDto;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMemberInfoService {
    private final MemberHelper memberHelper;
    private final MemberMapper memberMapper;

    public MemberInfoResDto execute(Long memberId) {
        MemberInfo memberInfo = memberHelper.findMemberInfoByIdOrElseThrow(memberId);
        return memberMapper.toMemberInfoResDto(memberInfo);
    }
}
