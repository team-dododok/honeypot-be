package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BadgeHelperTest {

    @InjectMocks
    private BadgeHelper badgeHelper;

    @Mock
    private BadgeRepository badgeRepository;


    @Test
    @DisplayName("존재하는 모든 뱃지 정보를 조회한다.")
    void getAllBadge() {
        //given
        List<AllBadgeInfo> allBadgeInfos = new ArrayList<>();
        allBadgeInfos.add(new AllBadgeInfo(1L, "https://example.com/images/badge1.png", "첫 꿀 받기", "받은 꿀 1회"));
        allBadgeInfos.add(new AllBadgeInfo(2L, "https://example.com/images/badge2.png", "꿀 좀 받아본 사람", "받은 꿀 7회"));
        allBadgeInfos.add(new AllBadgeInfo(3L, "https://example.com/images/badge3.png", "꿀부자", "받은 꿀 50회"));
        allBadgeInfos.add(new AllBadgeInfo(4L, "https://example.com/images/badge4.png", "첫 꿀 보내기", "보낸 꿀 1회"));
        allBadgeInfos.add(new AllBadgeInfo(5L, "https://example.com/images/badge5.png", "달달한 당신", "보낸 꿀 7회"));
        allBadgeInfos.add(new AllBadgeInfo(6L, "https://example.com/images/badge6.png", "꿀플루언서", "보낸 꿀 50회"));

        // when
        Mockito.when(badgeRepository.findAllBy()).thenReturn(allBadgeInfos);

        List<AllBadgeInfo> allBadge = badgeHelper.getAllBadge();

        // then

        Assertions.assertThat(allBadge.size()).isEqualTo(6);

    }
}