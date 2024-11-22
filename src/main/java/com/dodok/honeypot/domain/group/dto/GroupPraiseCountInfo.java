package com.dodok.honeypot.domain.group.dto;

public record GroupPraiseCountInfo(
        Long groupId,
        Long receiveCount,
        Long sendCount
) {
}
