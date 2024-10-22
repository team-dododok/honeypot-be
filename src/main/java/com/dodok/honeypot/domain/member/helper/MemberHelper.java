package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.repository.MemberRepository;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.dodok.honeypot.domain.member.error.MemberErrorCode.MEMBER_ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class MemberHelper {

    private final MemberRepository memberRepository;

    public MemberInfo findMemberInfoByIdOrElseThrow(Long memberId) {
        return memberRepository.findMemberDetailInfoByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_ENTITY_NOT_FOUND));
    }

    public Page<MemberInfo> findAllMembersByName(String name, Pageable pageable) {
        return memberRepository.findAllMembersByName(name, pageable);
    }
}
