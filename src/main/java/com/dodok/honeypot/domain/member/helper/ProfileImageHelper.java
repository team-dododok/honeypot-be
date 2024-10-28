package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.member.repository.ProfileImageRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.dodok.honeypot.domain.member.error.ProfileImageErrorCode.PROFILE_IMAGE_ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ProfileImageHelper {
    private final ProfileImageRepository profileImageRepository;

    public ProfileImage findProfileImageByIdOrElseThrow(Long profileImageId) {
        return profileImageRepository.findById(profileImageId)
                .orElseThrow(() -> new EntityNotFoundException(PROFILE_IMAGE_ENTITY_NOT_FOUND));
    }
}
