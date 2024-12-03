package com.dodok.honeypot.domain.sendpraise.service;

import com.dodok.honeypot.domain.sendpraise.dto.req.CheckSendPraiseSentReqDto;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.entity.SendStatus;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CheckSendPraiseSentService {
    public static final String MEMO_CHAT = "MemoChat"; // 나와의 채팅방
    public static final String DIRECT_CHAT = "DirectChat"; // 다른 사용자와의 1:1 채팅방
    public static final String MULTI_CHAT = "MultiChat"; // 다른 사용자들과의 그룹 채팅방
    public static final String OPEN_DIRECT_CHAT = "OpenDirectChat"; // 1:1 오픈채팅방
    public static final String OPEN_MULTI_CHAT = "OpenMultiChat"; // 그룹 오픈채팅방4

    private final SendPraiseHelper sendPraiseHelper;
    public void execute(CheckSendPraiseSentReqDto req) {
        SendPraise sendPraise = sendPraiseHelper.findByUuidOrElseThrow(req.praiseUuid());
        SendStatus newStatus = switch (req.CHAT_TYPE()) {
            case MEMO_CHAT -> SendStatus.MYSELF;
            case DIRECT_CHAT, OPEN_DIRECT_CHAT -> SendStatus.DIRECT;
            case MULTI_CHAT, OPEN_MULTI_CHAT -> SendStatus.GROUP;
            default -> SendStatus.FAIL;
        };

        sendPraise.updateSendStatus(newStatus);

        log.info("[kakao-talk callback api] requestBody praiseUuid: " + req.praiseUuid());
        log.info("[kakao-talk callback api] requestBody CHAT_TYPE: " + req.CHAT_TYPE());
        log.info("[kakao-talk callback api] requestBody HASH_CHAT_ID: " + req.HASH_CHAT_ID());
    }
}
