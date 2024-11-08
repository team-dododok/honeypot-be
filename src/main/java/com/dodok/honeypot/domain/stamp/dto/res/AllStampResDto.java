package com.dodok.honeypot.domain.stamp.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

/**
 * 전체 꿀도장 정보를 반환하는 dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record AllStampResDto(
       List<StampDto> stampDtos
) {
    public static AllStampResDto of(List<StampDto> stampDtos){
        return AllStampResDto.builder()
                .stampDtos(stampDtos)
                .build();
    }
}
