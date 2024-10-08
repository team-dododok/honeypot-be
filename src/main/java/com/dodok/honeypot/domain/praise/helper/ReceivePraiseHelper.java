package com.dodok.honeypot.domain.praise.helper;

import com.dodok.honeypot.domain.praise.repository.ReceivePraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceivePraiseHelper {

    private final ReceivePraiseRepository receivePraiseRepository;
}
