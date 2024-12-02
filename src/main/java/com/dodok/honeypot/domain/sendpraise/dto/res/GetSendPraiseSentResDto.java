package com.dodok.honeypot.domain.sendpraise.dto.res;

import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import lombok.AccessLevel;
import lombok.Builder;

/**
 * 보낸칭찬이 잘 전송되었는지 확인하는 콜백 api의 요청 dto
 * @param sendStatus
 */
@Builder(access = AccessLevel.PRIVATE)
public record GetSendPraiseSentResDto(
        SendStatus sendStatus
) {
    public static GetSendPraiseSentResDto of(SendStatus sendStatus) {
        return GetSendPraiseSentResDto.builder()
                .sendStatus(sendStatus)
                .build();
    }
}
