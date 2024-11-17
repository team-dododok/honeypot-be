package com.dodok.honeypot.domain.group.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access= AccessLevel.PRIVATE)
public record GroupMemberNameInfo(
        String name
) {
    public static GroupMemberNameInfo of(String name) {
        return GroupMemberNameInfo.builder()
                .name(name)
                .build();
    }
}
