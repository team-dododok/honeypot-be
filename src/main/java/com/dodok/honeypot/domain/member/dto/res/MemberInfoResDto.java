package com.dodok.honeypot.domain.member.dto.res;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.praise.dto.info.MemberPraiseInfo;
import lombok.AccessLevel;
import lombok.Builder;

import static com.dodok.honeypot.global.utils.UpdateValueUtils.updateValue;

@Builder(access = AccessLevel.PRIVATE)
public record MemberInfoResDto(
        Long memberId,
        String name,
        String email,
        String imageUrl,
        Long receivePraiseCount,
        Long sendPraiseCount,
        String bestStamp
) {
    public static MemberInfoResDto of(MemberInfo memberInfo, MemberPraiseInfo memberPraiseInfo) {
        return MemberInfoResDto.builder()
                .memberId(memberInfo.memberId())
                .name(memberInfo.name())
                .email(memberInfo.email())
                .imageUrl(memberInfo.imageUrl())
                .receivePraiseCount(memberPraiseInfo.receivePraiseCount())
                .sendPraiseCount(memberPraiseInfo.sendPraiseCount())
                .bestStamp(updateValue("", memberPraiseInfo.bestStampUrl()))
                .build();
    }
}
