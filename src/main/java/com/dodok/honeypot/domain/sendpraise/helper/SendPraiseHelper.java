package com.dodok.honeypot.domain.sendpraise.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.repository.SendPraiseRepository;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendPraiseHelper {

    private final SendPraiseRepository sendPraiseRepository;

    public SendPraise createSendPraise(String title, String content, Boolean projectStatus,
                                       String receiverName, Member sender, Group group, HoneyStamp honeyStamp){
        return sendPraiseRepository.save(
                SendPraise.createSendPraise(title, content, projectStatus, receiverName, sender, group, honeyStamp)
        );
    }
}
