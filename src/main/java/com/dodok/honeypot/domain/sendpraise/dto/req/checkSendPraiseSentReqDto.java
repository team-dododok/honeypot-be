package com.dodok.honeypot.domain.sendpraise.dto.req;

/**
 * 보낸칭찬이 잘 전송되었는지 확인하는 콜백 api의 요청 dto
 * @param praiseUuid
 */
public record checkSendPraiseSentReqDto(
        String praiseUuid
) {
}
