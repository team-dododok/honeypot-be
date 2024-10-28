package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.entity.BadgeComplete;
import com.dodok.honeypot.domain.badge.error.BadgeErrorCode;
import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import com.dodok.honeypot.domain.badge.repository.BadgeRepository;
import com.dodok.honeypot.domain.member.error.MemberErrorCode;
import com.dodok.honeypot.domain.member.repository.MemberRepository;
import com.dodok.honeypot.domain.praise.repository.SendPraiseRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 사용자가 뱃지 획득 조건을 달성했는지 확인하는 헬퍼 - 칭찬을 보낼 때
 */
@RequiredArgsConstructor
@Component
public class CheckSendPraiseBadgeHelper {

    private final BadgeCompleteRepository badgeCompleteRepository;
    private final BadgeRepository badgeRepository;

    private final SendPraiseRepository sendPraiseRepository;
    private final MemberRepository memberRepository;

    public static final List<Long> SEND_PRAISE_COUNTS = List.of(1L, 7L, 50L);
    public static final List<String> SEND_PRAISE_DESCRIPTIONS = List.of("보낸 꿀 1회", "보낸 꿀 7회", "보낸 꿀 50회");

    /**
     * 보낸 꿀(칭찬)이 있을 때 배지 달성 조건을 체크하고 업데이트를 진행하는 로직
     *
     * @param memberId 사용자의 memberId
     * @return // TODO : 배지 달성을 했을 때, 사용자(프론트)에게 어떻게 전달할 것인가?
     */
    public void updateSendPraiseBadge(Long memberId) {
        Long sendPraiseCount = sendPraiseRepository.countByMember_Id(memberId);

        for (int badgeLevel = 0; badgeLevel < SEND_PRAISE_COUNTS.size(); badgeLevel++) {
            Long count = SEND_PRAISE_COUNTS.get(badgeLevel);
            String description = SEND_PRAISE_DESCRIPTIONS.get(badgeLevel);

            if (Objects.equals(sendPraiseCount, count)) {
                badgeCompleteRepository.save(
                        getBadgeComplete(memberId, description)
                );
                break;
            }
        }
    }

    /**
     * BadgeComplete 엔티티를 생성하기 위해 예외처리를 진행하는 로직
     * @param memberId 사용자의 memberId
     * @param description 뱃지의 설명란
     * @return 생성된 BadgeComplete 엔티티
     */
    private BadgeComplete getBadgeComplete(Long memberId, String description) {
        return BadgeComplete.createBadgeComplete(
                badgeRepository.findByDescription(description).orElseThrow(
                        () -> new EntityNotFoundException(BadgeErrorCode.BADGE_ENTITY_NOT_FOUND)
                ),
                memberRepository.findById(memberId).orElseThrow(
                        () -> new EntityNotFoundException(MemberErrorCode.MEMBER_ENTITY_NOT_FOUND)
                )
        );
    }


}
