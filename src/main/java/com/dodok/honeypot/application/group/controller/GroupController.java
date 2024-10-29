package com.dodok.honeypot.application.group.controller;

import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.service.CreateGroupService;
import com.dodok.honeypot.domain.group.service.DeleteGroupService;
import com.dodok.honeypot.global.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final CreateGroupService createGroupService;
    private final DeleteGroupService deleteGroupService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createGroup(@RequestParam(name = "id") final Long memberId,
                                                          @RequestBody @Valid final GroupCreateReqDto requestDto) {
        createGroupService.execute(memberId, requestDto);
        return SuccessResponse.created(null);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<SuccessResponse<?>> deleteGroup(@RequestParam(name = "id") final Long memberId,
                                                          @PathVariable(name = "id") final Long groupId) {
        deleteGroupService.execute(memberId, groupId);
        return SuccessResponse.ok(null);
    }
}
