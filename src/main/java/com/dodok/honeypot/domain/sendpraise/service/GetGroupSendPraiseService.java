package com.dodok.honeypot.domain.sendpraise.service;


import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.member.helper.MemberHelper;
import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.dto.res.GetGroupSendPraiseResDto;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import com.dodok.honeypot.domain.sendpraise.mapper.SendPraiseMapper;
import com.dodok.honeypot.global.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetGroupSendPraiseService {

    public final GroupHelper groupHelper;
    public final MemberHelper memberHelper;
    public final SendPraiseHelper sendPraiseHelper;
    public final SendPraiseMapper sendPraiseMapper;

    public GetGroupSendPraiseResDto execute(Long memberId, Long groupId, Pageable pageable) {
        Member member = memberHelper.findMemberByIdOrElseThrow(memberId);
        groupHelper.validateIsMemberGroup(member, groupId);
        Page<SendPraiseInfo> receivePraiseInfos = sendPraiseHelper.getGroupSendPraiseInfos(groupId, pageable);
        return sendPraiseMapper.toGetGroupReceivePraiseResDto(receivePraiseInfos.getContent(), PageInfo.of(receivePraiseInfos));
    }
}
