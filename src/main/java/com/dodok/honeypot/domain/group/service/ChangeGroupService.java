package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.req.GroupChangeReqDto;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.helper.SendPraiseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ChangeGroupService {

    private final GroupHelper groupHelper;
    private final ReceivePraiseHelper receivePraiseHelper;
    private final SendPraiseHelper sendPraiseHelper;

    public void receivePraiseExecute(GroupChangeReqDto requestDto){
        Group group = groupHelper.findGroupByIdOrElseThrow(requestDto.groupId());
        List<ReceivePraise> receivePraiseList = requestDto.praiseIdList().stream().map(receivePraiseHelper::findByIdOrElseThrow).toList();
        receivePraiseList.forEach(receivePraise -> receivePraise.updateGroup(group));
    }

    public void sendPraiseExecute(GroupChangeReqDto requestDto) {
        Group group = groupHelper.findGroupByIdOrElseThrow(requestDto.groupId());
        List<SendPraise> sendPraiseList = requestDto.praiseIdList().stream().map(sendPraiseHelper::findByIdOrElseThrow).toList();
        sendPraiseList.forEach(sendPraise -> sendPraise.updateGroup(group));
    }
}
