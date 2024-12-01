package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.GroupMembersInfo;
import com.dodok.honeypot.domain.group.dto.res.GetMyGroupsResDto;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.group.mapper.GroupMapper;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMyGroupNameAndMembersService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final GroupMapper groupMapper;

    public GetMyGroupsResDto execute(Long memberId, List<Long> groupIds) {
        memberHelper.findMemberByIdOrElseThrow(memberId);
        List<GroupMembersInfo> groupInfos = groupHelper.findGroupNameAndMembers(memberId, groupIds);
        return groupMapper.toGetMyGroupsResDto(groupInfos);
    }
}
