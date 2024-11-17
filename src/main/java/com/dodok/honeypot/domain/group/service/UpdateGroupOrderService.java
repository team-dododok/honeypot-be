package com.dodok.honeypot.domain.group.service;


import com.dodok.honeypot.domain.group.dto.req.GroupOrderReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupOrderUpdateReqDto;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.global.error.exception.ForbiddenException;
import com.dodok.honeypot.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.dodok.honeypot.domain.group.error.GroupErrorCode.MEMBER_NOT_GROUP_OWNER_FORBIDDEN;
import static com.dodok.honeypot.domain.group.error.GroupErrorCode.NOT_VALID_GROUP_ORDER_IDX;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateGroupOrderService {

    private final MemberHelper memberHelper;

    public void execute(Long memberId, GroupOrderUpdateReqDto requestDto) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        List<Group> memberGroups = member.getGroups();
        validateOrderIdx(requestDto, memberGroups.size());
        validateAllIsMemberGroup(memberGroups, requestDto);
        updateGroupOrders(memberGroups, requestDto);
    }

    // 들어온 그룹의 Order가 멤버의 그룹 수와 비교해서 유효한지 확인하는 메서드
    private void validateOrderIdx(GroupOrderUpdateReqDto requestDto, Integer totalGroups) {
        Set<Integer> validOrderIdxSet = IntStream.rangeClosed(1, totalGroups)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> providedOrderIdxSet = requestDto.groupOrderList().stream()
                .map(GroupOrderReqDto::orderIdx)
                .collect(Collectors.toSet());

        if (!providedOrderIdxSet.equals(validOrderIdxSet)) {
            throw new InvalidValueException(NOT_VALID_GROUP_ORDER_IDX);
        }
    }

    // 모든 그룹이 멤버의 그룹이 맞는지 확인하는 메서드
    private void validateAllIsMemberGroup(List<Group> memberGroups, GroupOrderUpdateReqDto requestDto) {
        Set<Long> memberGroupIds = memberGroups.stream()
                .map(Group::getId)
                .collect(Collectors.toSet());
        if (!requestDto.groupOrderList().stream().allMatch(reqGroup -> memberGroupIds.contains(reqGroup.groupId()))) {
            throw new ForbiddenException(MEMBER_NOT_GROUP_OWNER_FORBIDDEN);
        }
    }

    // 그룹의 순서를 update하는 메서드
    private void updateGroupOrders(List<Group> memberGroups, GroupOrderUpdateReqDto requestDto) {
        Map<Long, Integer> groupOrderMap = requestDto.groupOrderList().stream()
                .collect(Collectors.toMap(GroupOrderReqDto::groupId, GroupOrderReqDto::orderIdx));

        for (Group group : memberGroups) {
            if (groupOrderMap.containsKey(group.getId())) {
                Integer newOrderIdx = groupOrderMap.get(group.getId());
                group.updateGroupOrder(newOrderIdx);
            }
        }
    }
}
