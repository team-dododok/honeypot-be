package com.dodok.honeypot.domain.group.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetGroupNameAndPraiseCountResDto(
        Long groupId,
        String groupName,
        Integer praiseCount
) {
    public static GetGroupNameAndPraiseCountResDto of(Long groupId, String groupName, Integer praiseCount) {
        return GetGroupNameAndPraiseCountResDto.builder()
                .groupId(groupId)
                .groupName(groupName)
                .praiseCount(praiseCount)
                .build();
    }
}
