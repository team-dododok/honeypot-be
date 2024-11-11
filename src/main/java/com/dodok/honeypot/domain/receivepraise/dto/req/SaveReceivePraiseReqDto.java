package com.dodok.honeypot.domain.receivepraise.dto.req;

/**
 * 받은 칭찬을 저장할 때 요청하는 dto
 * @param receiverId 칭찬을 저장할 사람의 memberId
 * @param praiseUuid 칭찬의 정보가 담긴 UUID
 */
public record SaveReceivePraiseReqDto(
        Long receiverId,
        String praiseUuid
) {
}
