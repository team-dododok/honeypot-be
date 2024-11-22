package com.dodok.honeypot.domain.member.service;

import com.dodok.honeypot.domain.member.dto.res.ProfileImageUrlResDto;
import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.member.helper.ProfileImageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetProfileImageService {
    private final ProfileImageHelper profileImageHelper;

    public ProfileImageUrlResDto getProfileImageUrl() {
        List<ProfileImage> profileImageUrlList = profileImageHelper.getProfileImageUrl();
        Map<Long, String> ProfileImageIdAndUrl = profileImageUrlList.stream().collect(Collectors.toMap(ProfileImage::getId, ProfileImage::getImageUrl));

        return ProfileImageUrlResDto.of(ProfileImageIdAndUrl);
    }
}
