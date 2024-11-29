package com.dodok.honeypot.application.sendpraise.controller;

import com.dodok.honeypot.domain.sendpraise.dto.req.SendPraiseReqDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.DetailSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.dto.res.SendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.service.CreateSendPraiseService;
import com.dodok.honeypot.domain.sendpraise.service.GetDetailSendPraiseService;
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
    private final GetDetailSendPraiseService getDetailSendPraiseService;

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
     * 보낸 꿀을 열었을 때 보여지는 팝업 조회 로직
     *
     * @param sendPraiseId 보낸 칭찬id
     */
    @GetMapping("/detail")
    public ResponseEntity<SuccessResponse<?>> popUpReceivePraise(@MemberId final Long memberId,
                                                                 @RequestParam(name = "id") final Long sendPraiseId) {
        final DetailSendPraiseResDto response = getDetailSendPraiseService.execute(memberId, sendPraiseId);
        return SuccessResponse.ok(response);
    }
}
