package com.dodok.honeypot.domain.badge.helper;

import com.dodok.honeypot.domain.badge.dto.AllBadgeInfo;
import com.dodok.honeypot.domain.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BadgeHelper {

    private final BadgeRepository badgeRepository;

    public List<AllBadgeInfo> getAllBadge(){
        return badgeRepository.findAllBy();
    }

}
