package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.dto.res.MemberInfoResDto;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.mapper.MemberMapper;
import com.dodok.honeypot.domain.praise.dto.info.MemberPraiseInfo;
import com.dodok.honeypot.domain.praise.helper.PraiseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMemberInfoService {
    private final MemberHelper memberHelper;
    private final MemberMapper memberMapper;
    private final PraiseHelper praiseHelper;

    public MemberInfoResDto execute(Long memberId) {
        MemberInfo memberInfo = memberHelper.findMemberInfoByIdOrElseThrow(memberId);
        MemberPraiseInfo memberPraiseInfo = praiseHelper.getMemberPraiseInfo(memberId);
        return memberMapper.toMemberInfoResDto(memberInfo, memberPraiseInfo);
    }
}
