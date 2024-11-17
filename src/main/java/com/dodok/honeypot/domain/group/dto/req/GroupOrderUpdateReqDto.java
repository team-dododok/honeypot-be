package com.dodok.honeypot.domain.group.dto.req;

import java.util.List;

public record GroupOrderUpdateReqDto(
        List<GroupOrderReqDto> groupOrderList
){
}
