package com.dodok.honeypot.global.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Slice;

@Builder(access = AccessLevel.PRIVATE)
public record SliceInfo(
        Boolean hasNextPage,
        Integer pageNumber
) {
    public static SliceInfo of(Slice<?> slice) {
        return SliceInfo.builder()
                .hasNextPage(slice.hasNext())
                .pageNumber(slice.getNumber())
                .build();
    }
}
