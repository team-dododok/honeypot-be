package com.dodok.honeypot.domain.group.helper;

import com.dodok.honeypot.domain.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GroupHelper {

    private final GroupRepository groupRepository;
}
