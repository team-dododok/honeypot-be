package com.dodok.honeypot.domain.praise.helper;

import com.dodok.honeypot.domain.praise.repository.SendPraiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendPraiseHelper {

    private final SendPraiseRepository sendPraiseRepository;
}
