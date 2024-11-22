package com.dodok.honeypot.application.stamp.controller;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import com.dodok.honeypot.domain.stamp.service.GetAllStampService;
import com.dodok.honeypot.domain.stamp.service.GetReceivedStampByGroupService;
import com.dodok.honeypot.domain.stamp.service.GetSentStampByGroupService;
import com.dodok.honeypot.global.auth.MemberId;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stamp")
public class StampController {

    private final GetAllStampService getAllStampService;
    private final GetSentStampByGroupService sentStampByGroupHelperService;
    private final GetReceivedStampByGroupService getReceivedStampByGroupService;

    /**
     * 전체 꿀도장의 종류를 반환하는 api
     * @return 전체 꿀도장
     */
    @GetMapping("")
    ResponseEntity<SuccessResponse<?>> getAllStamp() {
        AllStampResDto allStamp = getAllStampService.execute();
        return SuccessResponse.ok(allStamp);
    }

    /**
     * groupId에서 보낸 꿀도장의 종류를 반환하는 api
     * @param groupId 사용자가 찾고자 하는 그룹id
     * @return 꿀도장의 전체 개수와 종류
     */
    @GetMapping("/sent")
    ResponseEntity<SuccessResponse<?>> getSentStampByGroup(@MemberId final Long memberId,
                                                           @RequestParam(name = "group") Long groupId) {
        StampInfoByGroupResDto sentStampByGroup = sentStampByGroupHelperService.execute(memberId, groupId);
        return SuccessResponse.ok(sentStampByGroup);
    }

    /**
     * groupId에서 받은 꿀도장의 종류를 반환하는 api
     * @param groupId 사용자가 찾고자 하는 그룹id
     * @return 꿀도장의 전체 개수와 종류
     */
    @GetMapping("/received")
    ResponseEntity<SuccessResponse<?>> getReceivedStampByGroup(@MemberId final Long memberId,
                                                               @RequestParam(name = "group") Long groupId) {
        StampInfoByGroupResDto receivedStampByGroup = getReceivedStampByGroupService.execute(memberId, groupId);
        return SuccessResponse.ok(receivedStampByGroup);
    }

}
