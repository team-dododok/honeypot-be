package com.dodok.honeypot.domain.member.service;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.dto.res.MembersInfoResDto;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMembersInfoService {
    private final MemberHelper memberHelper;
    private final MemberMapper memberMapper;

    public MembersInfoResDto execute(String name, Pageable pageable) {
        Page<MemberInfo> memberInfos = memberHelper.findAllMembersByName(name, pageable);
        return memberMapper.toMemberGetResDto(memberInfos);
    }
}
