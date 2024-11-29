package com.dodok.honeypot.application.sendpraise.controller;

import com.dodok.honeypot.domain.sendpraise.dto.req.SendPraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.req.checkSendPraiseSentReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.service.CreateSendPraiseService;
import com.dodok.honeypot.domain.sendpraise.service.GetGroupSendPraiseService;
import com.dodok.honeypot.global.auth.MemberId;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/send-praise")
public class SendPraiseController {

    private final CreateSendPraiseService sendPraiseService;
    private final GetGroupSendPraiseService getGroupSendPraiseService;

    /**
     * 보낸 칭찬을 생성하는 api
     */
    @PostMapping("")
    public ResponseEntity<SuccessResponse<?>> createSendReceive(@MemberId final Long memberId,
                                                                @RequestBody final SendPraiseReqDto reqDto) {
        SendPraiseResDto sendPraise = sendPraiseService.execute(memberId, reqDto);
        return SuccessResponse.created(sendPraise);
    }

    /**
     * 그룹 별 보낸 칭찬을 보는 api
     */
    @GetMapping("/group")
    public ResponseEntity<SuccessResponse<?>> getGroupReceivePraise(@MemberId final Long memberId,
                                                                    @RequestParam(name = "groupId") final Long groupId,
                                                                    final Pageable pageable) {
        final GetGroupSendPraiseResDto response = getGroupSendPraiseService.execute(memberId, groupId, pageable);
        return SuccessResponse.ok(response);
    }

    /**
     * 칭찬이 성공적으로 전송되었는지 확인할 수 있는 카카오 콜백 api
     */
    @PostMapping("/check")
    public ResponseEntity<SuccessResponse<?>> checkSendPraiseSent(
            @RequestBody checkSendPraiseSentReqDto req
    ) {
        return SuccessResponse.ok("praise Uuid : "+req + "is Successfully Sent");
        // TODO : 프론트엔드에서 콜백 api 테스트 후
        // TODO : send-praise 엔티티에 isSent 추가 및 기본값 false로 설정
        // TODO : uuid를 통해 조회한 엔티티의 isSent값을 변경하는 로직 추가
    }

    @GetMapping("/check")
    public ResponseEntity<SuccessResponse<?>> getSendPraiseSent(
            @RequestBody checkSendPraiseSentReqDto req
    ) {
        // TODO : req.uuid를 통해 엔티티 조회
        // TODO : 엔티티의 isSent값을 반환하는 로직 만들기
        return null;
    }


}
