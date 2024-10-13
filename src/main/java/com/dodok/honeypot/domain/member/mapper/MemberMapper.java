package com.dodok.honeypot.domain.member.mapper;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.dto.res.MembersInfoResDto;
import com.dodok.honeypot.global.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MembersInfoResDto toMemberGetResDto(Page<MemberInfo> memberInfoPage) {
        PageInfo pageInfo = PageInfo.of(memberInfoPage);
        return MembersInfoResDto.of(memberInfoPage.getContent(), pageInfo);
    }
}
