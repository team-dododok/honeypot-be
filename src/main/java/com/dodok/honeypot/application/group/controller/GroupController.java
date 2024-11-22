package com.dodok.honeypot.application.group.controller;

import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupOrderUpdateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupUpdateReqDto;
import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
import com.dodok.honeypot.domain.group.service.*;
import com.dodok.honeypot.domain.receivepraise.dto.res.GetGroupReceivePraiseResDto;
import com.dodok.honeypot.domain.group.dto.res.GetAllMyGroupResDto;
import com.dodok.honeypot.domain.group.service.*;
import com.dodok.honeypot.global.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final CheckGroupNameService checkGroupNameService;
    private final GetAllMyGroupService getAllMyGroupService;
    private final GetGroupReceivePraiseService getGroupReceivePraiseService;

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

    @GetMapping("/check")
    public ResponseEntity<SuccessResponse<?>> checkGroupNameDuplicate(@RequestParam(name = "id") final Long memberId,
                                                                      @RequestParam(name = "groupName") final String groupName) {
        final CheckGroupNameResDto response = checkGroupNameService.execute(memberId, groupName);
        return SuccessResponse.ok(response);
    }

    @PatchMapping("/order")
    public ResponseEntity<SuccessResponse<?>> updateGroupOrder(@RequestParam(name = "id") final Long memberId,
                                                               @RequestBody final GroupOrderUpdateReqDto requestDto) {
        updateGroupOrderService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllMyGroup(@RequestParam(name = "id") final Long memberId) {
        final GetAllMyGroupResDto response = getAllMyGroupService.execute(memberId);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/receive-praise")
    public ResponseEntity<SuccessResponse<?>> getGroupReceivePraise(@RequestParam(name = "id") final Long memberId,
                                                                    @RequestParam(name = "groupId") final Long groupId,
                                                                    final Pageable pageable) {
        final GetGroupReceivePraiseResDto response = getGroupReceivePraiseService.execute(memberId, groupId, pageable);
        return SuccessResponse.ok(response);
    }

}
