package com.dodok.honeypot.domain.stamp.dto.res;

/**
 * 꿀도장 정보를 반환하는 dto
 */
public record StampDto(
        Long id,
        String imageUrl,
        String stampName

) {
}
