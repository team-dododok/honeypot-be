package com.dodok.honeypot.domain.group.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateGroupResDto(
        Long groupId
) {
    public static CreateGroupResDto of(Long groupId) {
        return CreateGroupResDto.builder()
                .groupId(groupId)
                .build();
    }

}
