package com.dodok.honeypot.application.member.controller;

import com.dodok.honeypot.domain.member.dto.req.MemberServiceConsentUpdateReqDto;
import com.dodok.honeypot.domain.member.dto.req.MemberUpdateReqDto;
import com.dodok.honeypot.domain.member.dto.res.GetMemberConsentResDto;
import com.dodok.honeypot.domain.member.dto.res.MemberInfoResDto;
import com.dodok.honeypot.domain.member.dto.res.MembersInfoResDto;
import com.dodok.honeypot.domain.member.dto.res.ProfileImageUrlResDto;
import com.dodok.honeypot.domain.member.service.*;
import com.dodok.honeypot.global.auth.MemberId;
import com.dodok.honeypot.global.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final GetMembersInfoService getMembersInfoService;
    private final GetMemberInfoService getMemberInfoService;
    private final UpdateMemberInfoService updateMemberInfoService;
    private final GetProfileImageService getProfileImageService;
    private final GetMemberConsentService getMemberConsentService;
    private final UpdateMemberServiceConsentService updateMemberServiceConsentService;

    @GetMapping("/info")
    public ResponseEntity<SuccessResponse<?>> getMemberInfo(@MemberId final Long memberId) {
        final MemberInfoResDto response = getMemberInfoService.execute(memberId);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessResponse<?>> getMembersInfo(@RequestParam("name") final String name,
                                                             final Pageable pageable) {
        final MembersInfoResDto response = getMembersInfoService.execute(name, pageable);
        return SuccessResponse.ok(response);
    }

    @PatchMapping
    public ResponseEntity<SuccessResponse<?>> updateMemberInfo(@MemberId final Long memberId,
                                                               @RequestBody final MemberUpdateReqDto requestDto) {
        updateMemberInfoService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }

    @GetMapping("/profile-image")
    public ResponseEntity<SuccessResponse<?>> findProfileImageUrl() {
        ProfileImageUrlResDto resDto = getProfileImageService.getProfileImageUrl();
        return SuccessResponse.ok(resDto);
    }

    @GetMapping("/service-consent")
    public ResponseEntity<SuccessResponse<?>> getMemberServiceConsent(@MemberId final Long memberId) {
        GetMemberConsentResDto response = getMemberConsentService.execute(memberId);
        return SuccessResponse.ok(response);
    }

    @PatchMapping("/service-consent")
    public ResponseEntity<SuccessResponse<?>> updateMemberServiceConsent(@MemberId final Long memberId,
                                                                         @RequestBody final MemberServiceConsentUpdateReqDto requestDto) {
        updateMemberServiceConsentService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }
}
