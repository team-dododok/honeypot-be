package com.dodok.honeypot.application.receivepraise.controller;

import com.dodok.honeypot.domain.receivepraise.dto.req.SaveReceivePraiseReqDto;
import com.dodok.honeypot.domain.receivepraise.service.DeleteReceivedPraiseService;
import com.dodok.honeypot.domain.receivepraise.service.SaveReceivePraiseService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receive-praise")
public class ReceivePraiseController {

    private final SaveReceivePraiseService saveReceivePraiseService;
    private final DeleteReceivedPraiseService deleteReceivedPraiseService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse<?>> checkAlreadySaved(@RequestParam(name = "uuid") String uuid) {
        return null;
    }

    /**
     * 받은 칭찬을 저장하는 로직
     * @param req
     * @return
     */
    @PostMapping("")
    public ResponseEntity<SuccessResponse<?>> saveReceivePraise(@RequestBody SaveReceivePraiseReqDto req) {
        saveReceivePraiseService.execute(req);
        return SuccessResponse.ok(null);
    }

    /**
     * receivedPraiseId를 통해 받은칭찬 엔티티를 삭제하는 로직
     * @param receivedPraiseId 받은 칭찬id
     */
    @DeleteMapping("")
    public ResponseEntity<SuccessResponse<?>> deleteReceivedPraise(
            @RequestParam(name = "id") final Long receivedPraiseId
    ) {
        deleteReceivedPraiseService.execute(receivedPraiseId);
        return SuccessResponse.ok(null);
    }
}
