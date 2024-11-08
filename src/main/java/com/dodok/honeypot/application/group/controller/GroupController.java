package com.dodok.honeypot.application.group.controller;

import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupOrderUpdateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupUpdateReqDto;
import com.dodok.honeypot.domain.group.service.CreateGroupService;
import com.dodok.honeypot.domain.group.service.DeleteGroupService;
import com.dodok.honeypot.domain.group.service.UpdateGroupOrderService;
import com.dodok.honeypot.domain.group.service.UpdateGroupService;
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
    private final UpdateGroupService updateGroupService;
    private final UpdateGroupOrderService updateGroupOrderService;

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

    @PatchMapping("/name")
    public ResponseEntity<SuccessResponse<?>> updateGroup(@RequestParam(name = "id") final Long memberId,
                                                          @RequestBody final GroupUpdateReqDto requestDto) {
        updateGroupService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }

    @PatchMapping("/order")
    public ResponseEntity<SuccessResponse<?>> updateGroupOrder(@RequestParam(name = "id") final Long memberId,
                                                               @RequestBody final GroupOrderUpdateReqDto requestDto) {
        updateGroupOrderService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }
}
