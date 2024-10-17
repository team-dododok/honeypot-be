package com.dodok.honeypot.domain.member.dto.res;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.global.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MembersInfoResDto(
        List<MemberInfo> memberInfos,
        PageInfo pageInfo
){
    public static MembersInfoResDto of(List<MemberInfo> memberInfos, PageInfo pageInfo) {
        return MembersInfoResDto.builder()
                .memberInfos(memberInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
