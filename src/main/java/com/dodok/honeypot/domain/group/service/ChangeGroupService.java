package com.dodok.honeypot.domain.group.service;

import com.dodok.honeypot.domain.group.dto.req.GroupChangeReqDto;
import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.helper.GroupHelper;
import com.dodok.honeypot.domain.receivepraise.entity.ReceivePraise;
import com.dodok.honeypot.domain.receivepraise.helper.ReceivePraiseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ChangeGroupService {

    private final GroupHelper groupHelper;
    private final ReceivePraiseHelper receivePraiseHelper;

    public void execute(GroupChangeReqDto requestDto){
        Group group = groupHelper.findGroupByIdOrElseThrow(requestDto.groupId());
        List<ReceivePraise> ReceivePraiseList = requestDto.praiseIdList().stream().map(receivePraiseHelper::findByIdOrElseThrow).collect(Collectors.toList());
        ReceivePraiseList.stream().forEach(receivePraise -> receivePraise.updateGroup(group));
    }
}
