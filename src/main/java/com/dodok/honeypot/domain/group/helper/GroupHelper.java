package com.dodok.honeypot.domain.group.helper;

import com.dodok.honeypot.domain.group.dto.GroupWithMembersInfo;
import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.repository.GroupRepository;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.global.error.exception.ConflictException;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dodok.honeypot.domain.group.entity.Group.createGroup;
import static com.dodok.honeypot.domain.group.error.GroupErrorCode.*;

@RequiredArgsConstructor
@Component
public class GroupHelper {

    private final GroupRepository groupRepository;

    public void validateDuplicateGroupName(Member member, String groupName) {
        if (groupRepository.existsByMemberAndName(member, groupName)) {
            throw new ConflictException(DUPLICATE_GROUP_NAME);
        }
    }

    public void createGroupAndSave(GroupCreateReqDto requestDto, Member member) {
        Integer groupCount = groupRepository.countByMember(member);
        Group group = createGroup(requestDto.groupName(), member, groupCount);
        groupRepository.save(group);
    }

    public Group findGroupByMemberAndIdOrElseThrow(Member member, Long groupId) {
        return groupRepository.findByMemberAndId(member, groupId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_GROUP_NOT_FOUND));
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public Group findGroupByIdOrElseThrow(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(GROUP_ENTITY_NOT_FOUND));
    }

    public Boolean checkGroupNameIsExist(Member member, String groupName) {
        return groupRepository.existsByMemberAndName(member, groupName);
    }

    public List<GroupWithMembersInfo> findGroupInfosByMemberId(Long memberId) {
        return groupRepository.findGroupInfosByMemberId(memberId);
    }

    public void validateIsMemberGroup(Member member, Long groupId) {
        if (!groupRepository.existsByMemberAndId(member, groupId)) {
            throw new EntityNotFoundException(MEMBER_GROUP_NOT_FOUND);
        }
    }
}
