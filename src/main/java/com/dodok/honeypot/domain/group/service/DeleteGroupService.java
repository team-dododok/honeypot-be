package com.dodok.honeypot.domain.group.service;


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
public class DeleteGroupService {

    private final MemberHelper memberHelper;
    private final GroupHelper groupHelper;

    public void execute(Long memberId, Long groupId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        Group group = groupHelper.findGroupByMemberAndIdOrElseThrow(member, groupId);
        groupHelper.deleteGroup(group);
    }

}
