package com.dodok.honeypot.domain.receivepraise.service;


import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ProfileImageHelper;
import com.dodok.honeypot.domain.receivepraise.dto.ReceivePraiseInfo;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import com.dodok.honeypot.domain.receivepraise.mapper.ReceivePraiseMapper;
import com.dodok.honeypot.global.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetGroupReceivePraiseService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final ReceivePraiseHelper receivePraiseHelper;
    private final ReceivePraiseMapper receivePraiseMapper;
    private final ProfileImageHelper profileImageHelper;

    public GetGroupReceivePraiseResDto execute(Long memberId, Long groupId, Pageable pageable) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        groupHelper.validateIsMemberGroup(member, groupId);
        Page<ReceivePraiseInfo> receivePraiseInfos = receivePraiseHelper.getGroupReceivePraiseInfos(groupId, pageable);
        List<ProfileImage> profileImageUrlList = profileImageHelper.getProfileImageUrl();
        return receivePraiseMapper.toGetGroupReceivePraiseResDto(receivePraiseInfos.getContent(), profileImageUrlList, PageInfo.of(receivePraiseInfos));
    }
}
