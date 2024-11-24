package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
import com.dodok.honeypot.domain.group.dto.res.GetSearchGroupNameResDto;
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
public class GetSearchGroupNameService {

    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;
    private final GroupMapper groupMapper;

    public GetSearchGroupNameResDto execute(Long memberId, String groupName) {
        memberHelper.findMemberByIdOrElseThrow(memberId);
        List<SearchGroupInfo> searchGroupInfos = groupHelper.findAllGroupByName(memberId, groupName);
        return groupMapper.toGetSearchGroupNameResDto(searchGroupInfos);
    }
}
