package com.dodok.honeypot.domain.group.dto.req;

import jakarta.validation.constraints.Size;

public record GroupCreateReqDto (
        @Size(min = 1, max = 15, message = "15자 이내로 입력해 주세요.")
        String groupName
){
}
