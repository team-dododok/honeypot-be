package com.dodok.honeypot.domain.member.dto.res;

import com.dodok.honeypot.domain.member.entity.ProfileImage;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.Map;

@Builder(access = AccessLevel.PRIVATE)
public record ProfileImageUrlResDto(
        Map<Long, String> profileImageUrl
) {
    public static ProfileImageUrlResDto of(Map<Long, String> profileImage) {
        return ProfileImageUrlResDto.builder()
                .profileImageUrl(profileImage)
                .build();
    }
}
