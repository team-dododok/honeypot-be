package com.dodok.honeypot.domain.group.helper;

import com.dodok.honeypot.domain.group.repository.GroupRepository;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.global.error.exception.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dodok.honeypot.domain.member.entity.Member.createEmptyMember;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupHelperTest {

    @InjectMocks
    private GroupHelper groupHelper;

    @Mock
    private GroupRepository groupRepository;

    private Member member;

    @BeforeEach
    void setup() {
        member = createEmptyMember();
    }

    @Test
    @DisplayName("그룹명 중복검증 확인하기")
    void validateDuplicateGroupName() {
        // given
        String groupName = "테스트 그룹명";

        // when
        when(groupRepository.existsByMemberAndName(member, groupName)).thenReturn(true);

        // then
        assertThrows(ConflictException.class,
                () -> groupHelper.validateDuplicateGroupName(member, groupName));
    }
}
