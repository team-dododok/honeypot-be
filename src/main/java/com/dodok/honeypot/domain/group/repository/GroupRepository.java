package com.dodok.honeypot.domain.group.repository;

import com.dodok.honeypot.domain.group.entity.Group;
import com.dodok.honeypot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>,
        GroupInfosQueryRepository,
        GroupNameSearchQueryRepository,
        GroupNameAndTotalCountQueryRepository {
    Optional<Group> findByIdAndDeletedAtIsNull(Long groupId);
    int countByMemberAndDeletedAtIsNull(Member member);
    boolean existsByMemberAndNameAndDeletedAtIsNull(Member member, String name);
    Optional<Group> findByMemberAndIdAndDeletedAtIsNull(Member member, Long id);

    boolean existsByMemberAndIdAndDeletedAtIsNull(Member member, Long id);

    List<Group> findAllByMemberAndDeletedAtIsNull(Member member);
}
