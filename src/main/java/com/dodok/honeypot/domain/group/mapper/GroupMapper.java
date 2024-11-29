package com.dodok.honeypot.domain.group.mapper;

import com.dodok.honeypot.domain.group.dto.GroupNamePraiseCountInfo;
import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
import com.dodok.honeypot.domain.group.dto.res.*;
import com.dodok.honeypot.domain.group.entity.Group;
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

    public GetGroupNameAndPraiseCountResDto toGetGroupNameAndPraiseCountResDto(GroupNamePraiseCountInfo groupInfo) {
        return GetGroupNameAndPraiseCountResDto.of(groupInfo.groupId(),
                groupInfo.name(),
                groupInfo.receiveCount() + groupInfo.sendCount());
    }

    public CreateGroupResDto toCreateGroupResDto(Group group) {
        return CreateGroupResDto.of(group.getId());
    }
}
