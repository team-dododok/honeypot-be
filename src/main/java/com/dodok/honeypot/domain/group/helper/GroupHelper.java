package com.dodok.honeypot.domain.group.helper;

import com.dodok.honeypot.domain.group.dto.GroupMembersInfo;
import com.dodok.honeypot.domain.group.dto.GroupMembersPraiseCountInfo;
import com.dodok.honeypot.domain.group.dto.GroupNamePraiseCountInfo;
import com.dodok.honeypot.domain.group.dto.SearchGroupInfo;
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
        if (groupRepository.existsByMemberAndNameAndDeletedAtIsNull(member, groupName)) {
            throw new ConflictException(DUPLICATE_GROUP_NAME);
        }
    }

    public Group createGroupAndSave(GroupCreateReqDto requestDto, Member member) {
        Integer groupCount = groupRepository.countByMemberAndDeletedAtIsNull(member);
        Group group = createGroup(requestDto.groupName(), member, groupCount);
        return groupRepository.save(group);
    }

    public Group findGroupByMemberAndIdOrElseThrow(Member member, Long groupId) {
        return groupRepository.findByMemberAndIdAndDeletedAtIsNull(member, groupId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_GROUP_NOT_FOUND));
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public Group findGroupByIdOrElseThrow(Long groupId) {
        return groupRepository.findByIdAndDeletedAtIsNull(groupId)
                .orElseThrow(() -> new EntityNotFoundException(GROUP_ENTITY_NOT_FOUND));
    }

    public Boolean checkGroupNameIsExist(Member member, String groupName) {
        return groupRepository.existsByMemberAndNameAndDeletedAtIsNull(member, groupName);
    }


    public List<GroupMembersPraiseCountInfo> findGroupInfosByMemberId(Long memberId) {
        return groupRepository.findGroupInfosByMemberIdAndDeletedAtIsNull(memberId);
    }

    public void validateIsMemberGroup(Member member, Long groupId) {
        if (!groupRepository.existsByMemberAndIdAndDeletedAtIsNull(member, groupId)) {
            throw new EntityNotFoundException(MEMBER_GROUP_NOT_FOUND);
        }
    }

    public List<SearchGroupInfo> findAllGroupByName(Long memberId, String name) {
        return groupRepository.findAllByNameContainsAndMemberAndDeletedAtIsNull(memberId, name);
    }

    public GroupNamePraiseCountInfo findNameAndPraiseCount(Long groupId, Member member) {
        return groupRepository.findNameAndPraiseCountAndDeletedAtIsNull(groupId, member)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_GROUP_NOT_FOUND));
    }

    public List<Group> findAllByMember(Member member) {
        return groupRepository.findAllByMemberAndDeletedAtIsNull(member);
    }
    public List<GroupMembersInfo> findGroupNameAndMembers(Long memberId, List<Long> groupIds) {
        return groupRepository.findGroupNameAndMembersAndDeletedAtIsNull(memberId, groupIds);
    }
}
