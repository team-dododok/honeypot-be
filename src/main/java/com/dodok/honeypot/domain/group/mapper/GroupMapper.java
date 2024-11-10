package com.dodok.honeypot.domain.group.mapper;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
import com.dodok.honeypot.domain.group.dto.res.GetAllMyGroupResDto;
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
}
