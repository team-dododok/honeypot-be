package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.dodok.honeypot.domain.group.dto.res.GetAllMyGroupResDto;
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
public class GetAllMyGroupService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final GroupMapper groupMapper;

    public GetAllMyGroupResDto execute(Long memberId) {
        memberHelper.findMemberByIdOrElseThrow(memberId);
        List<GroupWithMembersInfo> groupInfos = groupHelper.findGroupInfosByMemberId(memberId);
        return groupMapper.toGetAllMyGroupResDto(groupInfos);
    }
}
