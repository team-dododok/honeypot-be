package com.dodok.honeypot.domain.member.helper;

import com.dodok.honeypot.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberHelper {

    private final MemberRepository memberRepository;
}
