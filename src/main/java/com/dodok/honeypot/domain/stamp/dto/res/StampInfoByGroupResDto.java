package com.dodok.honeypot.domain.stamp.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

/**
 * 그룹별로 꿀도장 정보를 조회할 때 사용하는 res dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record StampInfoByGroupResDto(
        List<StampInfoByGroupDto> stampInfoByGroupDtos
) {
    public static StampInfoByGroupResDto of(List<StampInfoByGroupDto> stampInfoByGroupDtos) {
        return StampInfoByGroupResDto.builder()
                .stampInfoByGroupDtos(stampInfoByGroupDtos)
                .build();
    }
}
