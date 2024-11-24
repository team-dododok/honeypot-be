package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampInfoByGroupResDto;
import com.dodok.honeypot.domain.stamp.helper.StampByGroupHelper;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import com.dodok.honeypot.domain.stamp.mapper.StampByGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetSentStampByGroupService {
    private final StampByGroupHelper stampByGroupHelper;
    private final StampHelper stampHelper;
    private final GroupHelper groupHelper;
    private final MemberHelper memberHelper;

    private final StampByGroupMapper stampByGroupMapper;

    /**
     * 그룹을 기준으로 보낸 꿀도장을 확인하는 로직
     * @param groupId 찾을 그룹의 id
     * @return 해당 그룹에서 보낸 꿀도장 정보
     */
    public StampInfoByGroupResDto execute(Long memberId, Long groupId) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        groupHelper.validateIsMemberGroup(member, groupId);
        List<StampDto> allStamp = stampHelper.getAllStamp();
        List<StampDto> sentStampByGroup = stampByGroupHelper.getSentStampByGroup(groupId);

        return stampByGroupMapper.toStampInfoByGroupResDto(allStamp,sentStampByGroup);
    }
}
