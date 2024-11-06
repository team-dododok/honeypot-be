package com.dodok.honeypot.domain.group.service;


import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
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
public class CheckGroupNameService {

    public final GroupHelper groupHelper;
    public final MemberHelper memberHelper;
    public final GroupMapper groupMapper;

    public CheckGroupNameResDto execute(Long memberId, String groupName) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        Boolean isDuplicate = groupHelper.checkGroupNameIsExist(member, groupName);
        return groupMapper.toCheckGroupNameResDto(isDuplicate);
    }
}
