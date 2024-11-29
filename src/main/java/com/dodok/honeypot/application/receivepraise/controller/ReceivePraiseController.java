package com.dodok.honeypot.application.receivepraise.controller;

import com.dodok.honeypot.domain.receivepraise.dto.req.SaveReceivePraiseReqDto;

import com.dodok.honeypot.domain.receivepraise.dto.res.GetReceivedPraiseResDto;
import com.dodok.honeypot.domain.receivepraise.service.DeleteReceivedPraiseService;
import com.dodok.honeypot.domain.receivepraise.service.GetReceivedPraiseService;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.domain.receivepraise.service.GetGroupReceivePraiseService;
import com.dodok.honeypot.domain.receivepraise.service.SaveReceivePraiseService;
import com.dodok.honeypot.global.auth.JwtUtil;
import com.dodok.honeypot.global.auth.MemberId;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receive-praise")
public class ReceivePraiseController {

    private final SaveReceivePraiseService saveReceivePraiseService;
    private final DeleteReceivedPraiseService deleteReceivedPraiseService;
    private final GetReceivedPraiseService getReceivedPraiseService;
    private final JwtUtil jwtUtil;
    private final GetGroupReceivePraiseService getGroupReceivePraiseService;

    /**
     * 칭찬 받았을 때 정보 조회하는 api
     * @param uuid 칭찬 uuid
     * @param authorizationHeader 헤더
     * @return
     */
    @GetMapping("")
    public ResponseEntity<SuccessResponse<?>> getReceivedPraiseInfo(
            @RequestParam(name = "uuid") String uuid,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader
           ) {

        Optional<Long> optionalMemberId = authorizationHeader == null
                ? Optional.empty()
                : Optional.of(jwtUtil.getMemberIdFromAuthorizationHeader(authorizationHeader));

        GetReceivedPraiseResDto res = getReceivedPraiseService.execute(uuid, optionalMemberId);
        return SuccessResponse.ok(res);
    }

    /**
     * 받은 칭찬을 저장하는 로직
     *
     * @param req
     * @return
     */
    @PostMapping("")
    public ResponseEntity<SuccessResponse<?>> saveReceivePraise(@MemberId final Long memberId,
                                                                @RequestBody final SaveReceivePraiseReqDto req) {
        saveReceivePraiseService.execute(memberId, req);
        return SuccessResponse.ok(null);
    }

    /**
     * receivedPraiseId를 통해 받은칭찬 엔티티를 삭제하는 로직
     *
     * @param receivedPraiseId 받은 칭찬id
     */
    @DeleteMapping("")
    public ResponseEntity<SuccessResponse<?>> deleteReceivedPraise(@MemberId final Long memberId,
                                                                   @RequestParam(name = "id") final Long receivedPraiseId) {
        deleteReceivedPraiseService.execute(memberId, receivedPraiseId);
        return SuccessResponse.ok(null);
    }

    @GetMapping("/group")
    public ResponseEntity<SuccessResponse<?>> getGroupReceivePraise(@MemberId final Long memberId,
                                                                    @RequestParam(name = "groupId") final Long groupId,
                                                                    final Pageable pageable) {
        final GetGroupReceivePraiseResDto response = getGroupReceivePraiseService.execute(memberId, groupId, pageable);
        return SuccessResponse.ok(response);
    }


}
