package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.member.dto.info.MemberInfo;
import com.dodok.honeypot.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberHelper {

    private final MemberRepository memberRepository;

    public Page<MemberInfo> findAllMembersByName(String name, Pageable pageable) {
        return memberRepository.findAllMembersByName(name, pageable);
    }
}
