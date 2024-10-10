package com.dodok.honeypot.application.member.controller;

import com.dodok.honeypot.domain.member.dto.res.MembersInfoResDto;
import com.dodok.honeypot.domain.member.service.GetMembersInfoService;
import com.dodok.honeypot.global.entity.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final GetMembersInfoService getMembersInfoService;

    @GetMapping("/search")
    public ResponseEntity<SuccessResponse<?>> getMembersInfo(@RequestParam("name") final String name,
                                                            final Pageable pageable) {
        final MembersInfoResDto response = getMembersInfoService.execute(name, pageable);
        return SuccessResponse.ok(response);
    }
}
