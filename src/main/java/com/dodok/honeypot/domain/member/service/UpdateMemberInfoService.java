package com.dodok.honeypot.domain.member.service;


import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.dto.req.MemberUpdateReqDto;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ProfileImageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMemberInfoService {
    private final MemberHelper memberHelper;
    private final ProfileImageHelper profileImageHelper;

    public void execute(Long memberId, MemberUpdateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        ProfileImage profileImage;

        if (isUpdateRequestProfileImageIdIsNull(requestDto)) {
            profileImage = member.getProfileImage();
        }
        else {
            profileImage = profileImageHelper.findProfileImageByIdOrElseThrow(requestDto.profileImageId());
        }

        member.updateMember(requestDto.name(), profileImage);
    }

    private boolean isUpdateRequestProfileImageIdIsNull(MemberUpdateReqDto requestDto) {
        return Objects.isNull(requestDto.profileImageId());
    }
}
