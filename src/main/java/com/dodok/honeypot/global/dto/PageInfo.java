package com.dodok.honeypot.global.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder(access = AccessLevel.PRIVATE)
public record PageInfo(
        int pageNum,
        int pageSize,
        long totalElements,
        long totalPages
) {
    public static PageInfo of(Page<?> page) {
        return PageInfo.builder()
                .pageNum(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
