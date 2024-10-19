    package com.dodok.honeypot.domain.badge.repository;

    import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
    import com.dodok.honeypot.domain.badge.entity.QBadge;
    import com.dodok.honeypot.domain.badge.entity.QBadgeComplete;
    import com.querydsl.core.types.Projections;
    import com.querydsl.jpa.impl.JPAQueryFactory;
    import lombok.RequiredArgsConstructor;

    import java.util.List;


    @RequiredArgsConstructor
    public class BadgeInfoQueryRepositoryImpl implements BadgeInfoQueryRepository {
        private final JPAQueryFactory queryFactory;

        @Override
        public List<CompletedBadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {
            QBadgeComplete qBadgeComplete = QBadgeComplete.badgeComplete;
            QBadge qBadge = QBadge.badge;

            // TODO : fetch시 예외처리를 해야하는가?
            return queryFactory.select(Projections.constructor(CompletedBadgeInfo.class,
                            qBadge.id,
                            qBadgeComplete.createdAt))
                    .from(qBadgeComplete)
                    .join(qBadge).on(qBadgeComplete.badge.id.eq(qBadge.id))
                    .where(qBadgeComplete.member.id.eq(memberId))
                    .fetch();
        }
    }
