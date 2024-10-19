    package com.dodok.honeypot.domain.badge.repository;

    import com.dodok.honeypot.domain.badge.dto.BadgeInfo;
    import com.dodok.honeypot.domain.badge.entity.Badge;
    import com.dodok.honeypot.domain.badge.entity.BadgeComplete;
    import com.dodok.honeypot.domain.badge.entity.QBadge;
    import com.dodok.honeypot.domain.badge.entity.QBadgeComplete;
    import com.dodok.honeypot.domain.member.entity.QMember;
    import com.querydsl.core.types.Projections;
    import com.querydsl.jpa.impl.JPAQueryFactory;
    import lombok.RequiredArgsConstructor;

    import java.util.List;
    import java.util.stream.Collectors;

    import static java.util.stream.Collectors.toList;

    @RequiredArgsConstructor
    public class BadgeInfoQueryRepositoryImpl implements BadgeInfoQueryRepository {
        private final JPAQueryFactory queryFactory;

        @Override
        public List<BadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
            QBadgeComplete qBadgeComplete = QBadgeComplete.badgeComplete;
            QBadge qBadge = QBadge.badge;

            // TODO : fetch시 예외처리를 해야하는가?
            return queryFactory.select(Projections.constructor(BadgeInfo.class,
                            qBadge.id,
                            qBadge.name,
                            qBadge.description,
                            qBadge.imageUrl,
                            qBadgeComplete.createdAt))
                    .from(qBadgeComplete)
                    .join(qBadge).on(qBadgeComplete.badge.id.eq(qBadge.id))
                    .where(qBadgeComplete.member.id.eq(memberId))
                    .fetch();
        }
    }
