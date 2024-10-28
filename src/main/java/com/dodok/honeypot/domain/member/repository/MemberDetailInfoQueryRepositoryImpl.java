package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.dodok.honeypot.domain.member.entity.QMember.member;
import static com.dodok.honeypot.domain.member.entity.QProfileImage.profileImage;

@RequiredArgsConstructor
public class MemberDetailInfoQueryRepositoryImpl implements MemberDetailInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberInfo> findMemberDetailInfoByMemberId(Long memberId) {
        return Optional.ofNullable(queryFactory.
                select(Projections.constructor(MemberInfo.class,
                        member.id,
                        member.name,
                        member.email,
                        member.imageUrl.coalesce(profileImage.imageUrl)
                ))
                .from(member)
                .leftJoin(member.profileImage, profileImage)
                .where(eqMemberId(memberId))
                .fetchOne());
    }

    private static BooleanExpression eqMemberId(Long memberId) {
        return member.id.eq(memberId);
    }
}
