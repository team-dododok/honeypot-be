package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BadgeHelper {

    private final BadgeRepository badgeRepository;
}
