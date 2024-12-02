package com.dodok.honeypot.domain.sendpraise.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.domain.sendpraise.dto.SendPraiseInfo;
import com.dodok.honeypot.domain.sendpraise.entity.SendPraise;
import com.dodok.honeypot.domain.sendpraise.error.SendPraiseErrorCode;
import com.dodok.honeypot.domain.sendpraise.repository.SendPraiseRepository;
import com.dodok.honeypot.domain.stamp.entity.HoneyStamp;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SendPraiseHelper {

    private final SendPraiseRepository sendPraiseRepository;

    public SendPraise createSendPraise(String content, Boolean projectStatus,
                                       String receiverName, Member sender, Group group, HoneyStamp honeyStamp) {
        return sendPraiseRepository.save(
                SendPraise.createSendPraise(content, projectStatus, receiverName, sender, group, honeyStamp)
        );
    }

    /**
     * 보낸 칭찬의 uuid를 통해 보낸 칭찬을 찾는 메서드
     *
     * @param uuid
     * @return
     */
    public SendPraise findByUuidOrElseThrow(String uuid) {
        return sendPraiseRepository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException(SendPraiseErrorCode.SEND_PRAISE_ENTITY_NOT_FOUND)
        );
    }


    public Page<SendPraiseInfo> getGroupSendPraiseInfos(Long groupId, Pageable pageable) {
        return sendPraiseRepository.findSendPraiseInfosByGroupId(groupId, pageable);
    }

    public SendPraise findByIdOrElseThrow(Long sendPraiseId) {
        return sendPraiseRepository.findById(sendPraiseId).orElseThrow(
                () -> new EntityNotFoundException(SendPraiseErrorCode.SEND_PRAISE_ENTITY_NOT_FOUND)
        );
    }

    /**
     * groupId를 통해 조회한 SendPraise의 groupId를 전부 null로 만드는 로직(미사용)
     * @param groupId
     */
    public void setGroupIdToNullByGroupId(Long groupId) {
        List<SendPraise> sendPraises = sendPraiseRepository.findAllByGroupId(groupId);
        sendPraises.forEach(sendPraise -> sendPraise.updateGroup(null));
    }
}
