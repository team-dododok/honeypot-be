package com.dodok.honeypot.domain.receivepraise.dto.req;

/**
 * 받은 칭찬을 저장할 때 요청하는 dto
 * @param praiseUuid 칭찬의 정보가 담긴 UUID
 */
public record SaveReceivePraiseReqDto(
        String praiseUuid
) {
}
