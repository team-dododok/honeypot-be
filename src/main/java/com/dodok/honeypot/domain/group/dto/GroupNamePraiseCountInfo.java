package com.dodok.honeypot.domain.group.dto;

public record GroupNamePraiseCountInfo(
        Long groupId,
        String name,
        Integer receiveCount,
        Integer sendCount
) {
}
