package com.dodok.honeypot.domain.group.service;


import com.dodok.honeypot.domain.group.dto.req.GroupUpdateReqDto;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateGroupService {

    private final MemberHelper memberHelper;
    private final GroupHelper groupHelper;

    public void execute(Long memberId, GroupUpdateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        Group group = groupHelper.findGroupByIdOrElseThrow(requestDto.groupId());
        groupHelper.validateDuplicateGroupName(member, requestDto.groupName());
        group.updateGroup(requestDto);
    }
}
