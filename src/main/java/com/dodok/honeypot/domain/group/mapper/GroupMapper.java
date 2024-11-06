package com.dodok.honeypot.domain.group.mapper;

import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public CheckGroupNameResDto toCheckGroupNameResDto(Boolean isDuplicate) {
        return CheckGroupNameResDto.of(isDuplicate);
    }
}
