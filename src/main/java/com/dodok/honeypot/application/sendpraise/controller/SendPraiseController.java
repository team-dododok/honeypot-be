package com.dodok.honeypot.application.sendpraise.controller;

import com.dodok.honeypot.domain.sendpraise.dto.req.CheckSendPraiseSentReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.req.SendPraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetSendPraiseSentResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.service.CheckSendPraiseSentService;
import com.dodok.honeypot.domain.sendpraise.service.CreateSendPraiseService;
import com.dodok.honeypot.domain.sendpraise.service.GetGroupSendPraiseService;
import com.dodok.honeypot.domain.sendpraise.service.GetSendPraiseSentService;
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
    private final CheckSendPraiseSentService checkSendPraiseSentService;
    private final GetSendPraiseSentService getSendPraiseSentService;

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
            @RequestBody CheckSendPraiseSentReqDto req
    ) {
        checkSendPraiseSentService.execute(req);
        return SuccessResponse.ok(null);

    }

    @GetMapping("/check")
    public ResponseEntity<SuccessResponse<?>> getSendPraiseSent(
            @RequestParam(name = "praise-uuid") String praiseUuid
    ) {
        GetSendPraiseSentResDto res = getSendPraiseSentService.execute(praiseUuid);
        return SuccessResponse.ok(res);
    }


}
