package com.dodok.honeypot.domain.group.helper;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.group.repository.GroupRepository;
import com.dodok.honeypot.domain.member.entity.Member;
import com.dodok.honeypot.global.error.exception.ConflictException;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dodok.honeypot.domain.group.entity.Group.createGroup;
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
    private Group group;

    @BeforeEach
    void setup() {
        member = createEmptyMember();
        group = createGroup("테스트", member, 1);
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

    @Test
    @DisplayName("그룹id와 member로 그룹 찾기")
    void findGroupByMemberAndIdOrElseThrow() {
        // given

        // when
        when(groupRepository.findByMemberAndId(member, group.getId())).thenReturn(Optional.ofNullable(group));
        Group findGroup = groupHelper.findGroupByMemberAndIdOrElseThrow(member, group.getId());

        // then
        Assertions.assertThat(findGroup).isEqualTo(group);
    }

    @Test
    @DisplayName("그룹id로 그룹 찾기")
    void findGroupByIdOrElseThrow() {
        // given

        // when
        when(groupRepository.findById(group.getId())).thenReturn(Optional.ofNullable(group));
        Group findGroup = groupHelper.findGroupByIdOrElseThrow(group.getId());

        // then
        Assertions.assertThat(findGroup).isEqualTo(group);
    }

    @Test
    @DisplayName("그룹id로 그룹 찾기 예외")
    void findGroupByIdOrElseThrowException() {
        // given

        // when
        when(groupRepository.findById(2L)).thenReturn(Optional.ofNullable(null));

        // then
        assertThrows(EntityNotFoundException.class,
                () -> groupHelper.findGroupByIdOrElseThrow(2L));
    }
}
