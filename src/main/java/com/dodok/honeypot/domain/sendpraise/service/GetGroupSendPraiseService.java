package com.dodok.honeypot.domain.sendpraise.service;


import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.entity.ProfileImage;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.member.helper.ProfileImageHelper;
import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.domain.sendpraise.mapper.SendPraiseMapper;
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
public class GetGroupSendPraiseService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final SendPraiseHelper sendPraiseHelper;
    private final SendPraiseMapper sendPraiseMapper;
    private final ProfileImageHelper profileImageHelper;

    public GetGroupSendPraiseResDto execute(Long memberId, Long groupId, Pageable pageable) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        groupHelper.validateIsMemberGroup(member, groupId);
        Page<SendPraiseInfo> receivePraiseInfos = sendPraiseHelper.getGroupSendPraiseInfos(groupId, pageable);
        List<ProfileImage> profileImageUrlList = profileImageHelper.getProfileImageUrl();
        return sendPraiseMapper.toGetGroupReceivePraiseResDto(receivePraiseInfos.getContent(), profileImageUrlList, PageInfo.of(receivePraiseInfos));
    }
}
