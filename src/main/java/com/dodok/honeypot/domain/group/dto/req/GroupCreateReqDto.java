package com.dodok.honeypot.domain.group.dto.req;

import jakarta.validation.constraints.Size;

public record GroupCreateReqDto (
        @Size(min = 2, max = 15, message = "그룹명은 최소 2자 ~ 최대 15자 입니다.")
        String groupName
){
}
