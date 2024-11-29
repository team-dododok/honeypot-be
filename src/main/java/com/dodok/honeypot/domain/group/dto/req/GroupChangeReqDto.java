package com.dodok.honeypot.domain.group.dto.req;


import java.util.List;

public record GroupChangeReqDto(
        List<Long> praiseIdList,//변경하려는 칭찬id
        Long groupId//변경할 그룹 id
) {
}
