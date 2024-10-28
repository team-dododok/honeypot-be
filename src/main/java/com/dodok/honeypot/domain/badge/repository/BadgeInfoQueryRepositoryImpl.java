    package com.dodok.honeypot.domain.badge.repository;

    import com.dodok.honeypot.domain.badge.dto.CompletedBadgeInfo;
    import com.querydsl.core.types.Projections;
    import com.querydsl.jpa.impl.JPAQueryFactory;
    import lombok.RequiredArgsConstructor;

    import java.util.List;

    import static com.dodok.honeypot.domain.badge.entity.QBadge.badge;
    import static com.dodok.honeypot.domain.badge.entity.QBadgeComplete.badgeComplete;


    @RequiredArgsConstructor
    public class BadgeInfoQueryRepositoryImpl implements BadgeInfoQueryRepository {
        private final JPAQueryFactory queryFactory;

        @Override
        public List<CompletedBadgeInfo> findAllCompletedBadgeByMemberId(Long memberId) {

            return queryFactory.select(Projections.constructor(CompletedBadgeInfo.class,
                            badge.id,
                            badge.description,
                            badgeComplete.createdAt))
                    .from(badgeComplete)
                    .join(badge).on(badgeComplete.badge.id.eq(badge.id))
                    .where(badgeComplete.member.id.eq(memberId))
                    .fetch();
        }
    }
