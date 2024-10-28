    package com.dodok.honeypot.domain.badge.service;

    import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
    import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
    import com.dodok.honeypot.domain.badge.dto.res.AllBadgeResDto;
    import com.dodok.honeypot.domain.badge.dto.res.BadgeResDto;
    import com.dodok.honeypot.domain.badge.helper.BadgeCompleteHelper;
    import com.dodok.honeypot.domain.badge.helper.BadgeHelper;
    import com.dodok.honeypot.domain.badge.mapper.BadgeCompleteMapper;
    import org.assertj.core.api.Assertions;
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
    class GetBadgeInfoServiceTest {

        @InjectMocks
        private GetBadgeInfoService getBadgeInfoService;

        @Mock
        private BadgeCompleteMapper badgeCompleteMapper;

        @Mock
        private BadgeHelper badgeHelper;
        @Mock
        private BadgeCompleteHelper badgeCompleteHelper;

        @Test
        @DisplayName("memberId를 통해 해당 사용자가 획득한 뱃지를 조회한다")
        void execute() {
            // given
            Long memberId = 1L;

            List<AllBadgeInfo> allBadgeInfos = new ArrayList<>();
            allBadgeInfos.add(new AllBadgeInfo(1L, "https://example.com/images/badge1.png", "첫 꿀 받기", "받은 꿀 1회"));
            allBadgeInfos.add(new AllBadgeInfo(2L, "https://example.com/images/badge2.png", "꿀 좀 받아본 사람", "받은 꿀 7회"));
            allBadgeInfos.add(new AllBadgeInfo(3L, "https://example.com/images/badge3.png", "꿀부자", "받은 꿀 50회"));
            allBadgeInfos.add(new AllBadgeInfo(4L, "https://example.com/images/badge4.png", "첫 꿀 보내기", "보낸 꿀 1회"));
            allBadgeInfos.add(new AllBadgeInfo(5L, "https://example.com/images/badge5.png", "달달한 당신", "보낸 꿀 7회"));
            allBadgeInfos.add(new AllBadgeInfo(6L, "https://example.com/images/badge6.png", "꿀플루언서", "보낸 꿀 50회"));

            List<CompletedBadgeInfo> completedBadgeInfos = new ArrayList<>();
            completedBadgeInfos.add(new CompletedBadgeInfo(1L, LocalDateTime.of(2024, 2, 13, 17, 36, 37)));
            completedBadgeInfos.add(new CompletedBadgeInfo(4L, LocalDateTime.of(2023, 2, 13, 17, 36, 37)));

            List<BadgeResDto> badgeResDtos = new ArrayList<>();
            badgeResDtos.add(BadgeResDto.of("첫 꿀 받기", "받은 꿀 1회", "https://example.com/images/badge1.png", LocalDateTime.of(2024, 2, 13, 17, 36, 37)));
            badgeResDtos.add(BadgeResDto.of("꿀 좀 받아본 사람", "받은 꿀 7회", "https://example.com/images/badge2.png", null));
            badgeResDtos.add(BadgeResDto.of("꿀부자", "받은 꿀 50회", "https://example.com/images/badge3.png", null));
            badgeResDtos.add(BadgeResDto.of("첫 꿀 보내기", "보낸 꿀 1회", "https://example.com/images/badge4.png", LocalDateTime.of(2023, 2, 13, 17, 36, 37)));
            badgeResDtos.add(BadgeResDto.of("달달한 당신", "보낸 꿀 7회", "https://example.com/images/badge5.png", null));
            badgeResDtos.add(BadgeResDto.of("꿀플루언서", "보낸 꿀 50회", "https://example.com/images/badge6.png", null));
            AllBadgeResDto expectedResponse = AllBadgeResDto.of(badgeResDtos);

            // when
            Mockito.when(badgeHelper.getAllBadge()).thenReturn(allBadgeInfos);
            Mockito.when(badgeCompleteHelper.findAllCompletedBadgeByMemberId(memberId)).thenReturn(completedBadgeInfos);
            Mockito.when(badgeCompleteMapper.toBadgeResDto(allBadgeInfos, completedBadgeInfos)).thenReturn(AllBadgeResDto.of(badgeResDtos));

            // then
            AllBadgeResDto execute = getBadgeInfoService.execute(memberId);
            Assertions.assertThat(execute).isEqualTo(expectedResponse);
        }
    }