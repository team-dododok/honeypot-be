package com.dodok.honeypot.domain.stamp.dto.req;

import lombok.AccessLevel;
import lombok.Builder;

/**
 * 그룹별로 꿀도장 정보를 조회할 때 사용하는 req dto
 */
@Builder(access = AccessLevel.PRIVATE)
public record StampInfoByGroupReqDto(
        Long groupId
) {
}
