package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.GroupNamePraiseCountInfo;
import com.dodok.honeypot.domain.group.dto.res.GetGroupNameAndPraiseCountResDto;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.group.mapper.GroupMapper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetGroupNameAndTotalPraiseCountService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final GroupMapper groupMapper;

    public GetGroupNameAndPraiseCountResDto execute(Long memberId, Long groupId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        GroupNamePraiseCountInfo groupInfo = groupHelper.findNameAndPraiseCount(groupId, member);
        return groupMapper.toGetGroupNameAndPraiseCountResDto(groupInfo);
    }
}
