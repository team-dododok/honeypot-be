package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BadgeCompleteHelperTest {

    @InjectMocks
    private BadgeCompleteHelper badgeCompleteHelper;

    @Mock
    private  BadgeCompleteRepository badgeCompleteRepository;

    @Test
    @DisplayName("memberId로 해당 사용자가 획득한 뱃지의 정보를 조회하기_실패")
    // TODO : 존재하지 않는 memberId인 경우 예외처리 하는 로직
    @Disabled
    void findAllCompletedBadgeByMemberId_Fail() {
        // given
        Long memberId = 978965216L;


        List<CompletedBadgeInfo> completedBadgeInfos = new ArrayList<>();

        // when & then
        Mockito.when(badgeCompleteRepository.findAllCompletedBadgeByMemberId(memberId)).thenReturn(completedBadgeInfos);

        Assertions.assertThatThrownBy(() -> badgeCompleteHelper.findAllCompletedBadgeByMemberId(memberId))
                .isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    @DisplayName("memberId로 해당 사용자가 획득한 뱃지의 정보를 조회하기_성공")
    void findAllCompletedBadgeByMemberId_Success() {
        // given
        Long memberId = 1L;

        List<CompletedBadgeInfo> completedBadgeInfos = new ArrayList<>();
        completedBadgeInfos.add(new CompletedBadgeInfo(1L, LocalDateTime.of(2024, 2, 13, 17, 36, 37)));
        completedBadgeInfos.add(new CompletedBadgeInfo(4L, LocalDateTime.of(2023, 2, 13, 17, 36, 37)));

        // when
        Mockito.when(badgeCompleteRepository.findAllCompletedBadgeByMemberId(memberId)).thenReturn(completedBadgeInfos);

        // then
        List<CompletedBadgeInfo> allCompletedBadgeByMemberId = badgeCompleteHelper.findAllCompletedBadgeByMemberId(memberId);
        Assertions.assertThat(allCompletedBadgeByMemberId.size()).isEqualTo(2);


    }
}