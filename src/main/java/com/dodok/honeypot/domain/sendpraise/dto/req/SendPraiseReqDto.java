package com.dodok.honeypot.domain.sendpraise.dto.req;

/**
 * 보낸 칭찬을 만들기 위한 정보
 */
public record SendPraiseReqDto(
        String title,
        String content,
        Boolean projectStatus,
        String receiverName,
        Long groupId,
        Long honeyStampId
) {
}
