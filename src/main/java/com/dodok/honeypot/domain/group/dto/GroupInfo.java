package com.dodok.honeypot.domain.group.dto;

public record GroupInfo(
        Long groupId,
        String groupName,
        String memberName,
        Integer orderIdx
) {
}
