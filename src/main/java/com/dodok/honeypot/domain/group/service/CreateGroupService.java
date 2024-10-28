package com.dodok.honeypot.domain.group.service;


import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateGroupService {

    private final MemberHelper memberHelper;
    private final GroupHelper groupHelper;

    public void execute(Long memberId, GroupCreateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        groupHelper.validateDuplicateGroupName(member, requestDto.groupName());
        groupHelper.createGroupAndSave(requestDto, member);
    }
}
