package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.repository.BadgeCompleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeCompleteHelper {

    private final BadgeCompleteRepository badgeCompleteRepository;
}
