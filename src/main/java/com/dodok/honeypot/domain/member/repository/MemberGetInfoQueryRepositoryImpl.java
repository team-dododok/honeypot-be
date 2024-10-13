package com.dodok.honeypot.domain.member.repository;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.dodok.honeypot.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberGetInfoQueryRepositoryImpl implements MemberGetInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberInfo> findAllMembersByName(String name, Pageable pageable) {
        List<MemberInfo> contents = queryFactory.
                select(Projections.constructor(MemberInfo.class,
                        member.id,
                        member.name,
                        member.email
                ))
                .from(member)
                .where(contianName(name))
                .orderBy(memberIdAsc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.id.countDistinct())
                .where(contianName(name))
                .from(member);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private static OrderSpecifier<Long> memberIdAsc() {
        return member.id.asc();
    }

    private BooleanExpression contianName(String name) {
        return member.name.contains(name);
    }
}
