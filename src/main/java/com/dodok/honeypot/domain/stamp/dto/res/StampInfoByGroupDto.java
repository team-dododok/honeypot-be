package com.dodok.honeypot.domain.stamp.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

/**
 * 그룹별로 꿀도장 정보를 조회할 때 사용하는 dto
 * @param imageUrl 꿀도장 이미지
 * @param stampName 꿀도장 이름
 * @param count 현재 받은 개수
 * @param totalCount 전체 개수
 */
@Builder(access = AccessLevel.PRIVATE)
public record StampInfoByGroupDto(
        String imageUrl,
        String stampName,
        Integer count,
        Integer totalCount
) {
    public static StampInfoByGroupDto of(String imageUrl, String stampName, Integer count, Integer totalCount) {
        return StampInfoByGroupDto.builder()
                .imageUrl(imageUrl)
                .stampName(stampName)
                .count(count)
                .totalCount(totalCount)
                .build();
    }
}
