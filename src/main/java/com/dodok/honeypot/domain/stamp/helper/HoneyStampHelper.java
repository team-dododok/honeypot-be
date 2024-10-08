package com.dodok.honeypot.domain.stamp.helper;

import com.dodok.honeypot.domain.stamp.repository.HoneyStampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HoneyStampHelper {

    private final HoneyStampRepository honeyStampRepository;
}
