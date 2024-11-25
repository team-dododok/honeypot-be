package com.dodok.honeypot.domain.group.mapper;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
import com.dodok.honeypot.domain.group.dto.res.GetAllMyGroupResDto;
import com.dodok.honeypot.domain.group.dto.res.GetSearchGroupNameResDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupMapper {
    public CheckGroupNameResDto toCheckGroupNameResDto(Boolean isDuplicate) {
        return CheckGroupNameResDto.of(isDuplicate);
    }

    public GetAllMyGroupResDto toGetAllMyGroupResDto(List<GroupWithMembersInfo> groupInfos) {
        return GetAllMyGroupResDto.of(groupInfos);
    }

    public GetSearchGroupNameResDto toGetSearchGroupNameResDto(List<SearchGroupInfo> groupInfos) {
        return GetSearchGroupNameResDto.of(groupInfos);
    }
}
