package com.dodok.honeypot.domain.sendpraise.dto.req;

/**
 * 칭찬 메시지 전송 여부 확인 요청 DTO.
 *
 * 필드 설명:
 * - CHAT_TYPE: 카카오톡 공유 메시지가 전달된 채팅방의 유형.
 *     - 가능한 값:
 *         - MemoChat: 나와의 채팅방
 *         - DirectChat: 다른 사용자와의 1:1 채팅방
 *         - MultiChat: 다른 사용자들과의 그룹 채팅방
 *         - OpenDirectChat: 1:1 오픈채팅방
 *         - OpenMultiChat: 그룹 오픈채팅방
 *
 * - HASH_CHAT_ID: 카카오톡 공유 메시지를 수신한 채팅방의 참고용 ID.
 *     - 서비스별로 유일(Unique)한 해시(Hash) 값으로, 같은 채팅방이라도 서비스마다 다른 값 제공.
 *
 * - TEMPLATE_ID: 메시지 템플릿 ID를 사용해 카카오톡 공유 메시지를 보낸 경우 사용된 템플릿의 ID.
 *     - 메시지 템플릿 ID를 사용하지 않은 경우 해당 값은 전달되지 않음.
 *
 * - praiseUuid: 칭찬 메시지의 UUID.
 */
public record CheckSendPraiseSentReqDto(
        String CHAT_TYPE,   // 채팅방 유형 (MemoChat, DirectChat 등)
        String HASH_CHAT_ID, // 채팅방 참고용 ID
        String TEMPLATE_ID,  // 사용된 메시지 템플릿 ID
        String praiseUuid    // 칭찬 메시지의 UUID
) {
}
