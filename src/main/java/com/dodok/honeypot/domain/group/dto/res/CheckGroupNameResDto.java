package com.dodok.honeypot.domain.group.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CheckGroupNameResDto(
        Boolean isDuplicate
) {
    public static CheckGroupNameResDto of(Boolean isDuplicate) {
        return CheckGroupNameResDto.builder()
                .isDuplicate(isDuplicate)
                .build();
    }
}
