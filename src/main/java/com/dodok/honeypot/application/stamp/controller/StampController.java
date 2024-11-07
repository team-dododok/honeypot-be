package com.dodok.honeypot.application.stamp.controller;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import com.dodok.honeypot.domain.stamp.service.SendStampByGroupHelperService;
import com.dodok.honeypot.domain.stamp.service.StampService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stamp")
public class StampController {

    private final StampService stampService;
    private final SendStampByGroupHelperService sendStampByGroupHelperService;

    /**
     * 전체 꿀도장의 종류를 반환하는 api
     * @return 전체 꿀도장
     */
    @GetMapping("")
    ResponseEntity<SuccessResponse<?>> getAllStamp() {
        AllStampResDto allStamp = stampService.execute();
        return SuccessResponse.ok(allStamp);
    }

    /**
     * groupId에서 받은 꿀도장의 종류를 반환하는 api
     * @param groupId 사용자가 찾고자 하는 그룹id
     * @return 꿀도장의 전체 개수와 종류
     */
    @GetMapping("/send")
    ResponseEntity<SuccessResponse<?>> getSendStampByGroup(@RequestParam(name = "group") Long groupId) {
        StampInfoByGroupResDto sendStampByGroup = sendStampByGroupHelperService.execute(groupId);
        return SuccessResponse.ok(sendStampByGroup);
    }
}
