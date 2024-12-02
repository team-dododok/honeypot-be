package com.dodok.honeypot.domain.group.dto.res;

import com.dodok.honeypot.domain.group.dto.GroupMembersPraiseCountInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAllMyGroupResDto(
        List<GroupMembersPraiseCountInfo> groupMembersPraiseCountInfos
) {
    public static GetAllMyGroupResDto of(List<GroupMembersPraiseCountInfo> groupMembersPraiseCountInfos) {
        return GetAllMyGroupResDto.builder()
                .groupMembersPraiseCountInfos(groupMembersPraiseCountInfos)
                .build();

    }
}
