package com.dodok.honeypot.application.group.controller;

import com.dodok.honeypot.domain.group.dto.req.GroupChangeReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupCreateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupOrderUpdateReqDto;
import com.dodok.honeypot.domain.group.dto.req.GroupUpdateReqDto;
import com.dodok.honeypot.domain.group.dto.res.CheckGroupNameResDto;
import com.dodok.honeypot.domain.group.dto.res.GetAllMyGroupResDto;
import com.dodok.honeypot.domain.group.dto.res.GetGroupNameAndPraiseCountResDto;
import com.dodok.honeypot.domain.group.dto.res.GetSearchGroupNameResDto;
import com.dodok.honeypot.domain.group.service.*;
import com.dodok.honeypot.global.auth.MemberId;
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
    private final CheckGroupNameService checkGroupNameService;
    private final GetAllMyGroupService getAllMyGroupService;
    private final GetSearchGroupNameService getSearchGroupNameService;
    private final GetGroupNameAndTotalPraiseCountService getGroupNameAndTotalPraiseCountService;
    private final ChangeGroupService changeGroupService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createGroup(@MemberId final Long memberId,
                                                          @RequestBody @Valid final GroupCreateReqDto requestDto) {
        createGroupService.execute(memberId, requestDto);
        return SuccessResponse.created(null);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<SuccessResponse<?>> deleteGroup(@MemberId final Long memberId,
                                                          @PathVariable(name = "id") final Long groupId) {
        deleteGroupService.execute(memberId, groupId);
        return SuccessResponse.ok(null);
    }

    @PatchMapping("/name")
    public ResponseEntity<SuccessResponse<?>> updateGroup(@MemberId final Long memberId,
                                                          @RequestBody final GroupUpdateReqDto requestDto) {
        updateGroupService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }

    @GetMapping("/check")
    public ResponseEntity<SuccessResponse<?>> checkGroupNameDuplicate(@MemberId final Long memberId,
                                                                      @RequestParam(name = "groupName") final String groupName) {
        final CheckGroupNameResDto response = checkGroupNameService.execute(memberId, groupName);
        return SuccessResponse.ok(response);
    }

    @PatchMapping("/order")
    public ResponseEntity<SuccessResponse<?>> updateGroupOrder(@MemberId final Long memberId,
                                                               @RequestBody final GroupOrderUpdateReqDto requestDto) {
        updateGroupOrderService.execute(memberId, requestDto);
        return SuccessResponse.ok(null);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllMyGroup(@MemberId final Long memberId) {
        final GetAllMyGroupResDto response = getAllMyGroupService.execute(memberId);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessResponse<?>> getSearchGroupName(@MemberId final Long memberId,
                                                                 @RequestParam("groupName") final String groupName) {
        final GetSearchGroupNameResDto response = getSearchGroupNameService.execute(memberId, groupName);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<SuccessResponse<?>> getGroupNameAndTotalPraiseCount(@MemberId final Long memberId,
                                                                              @RequestParam("groupId") final Long groupId) {
        final GetGroupNameAndPraiseCountResDto response = getGroupNameAndTotalPraiseCountService.execute(memberId, groupId);
        return SuccessResponse.ok(response);
    }

    @PatchMapping("/receive-praise/change")
    public ResponseEntity<SuccessResponse<?>> changeGroup(@RequestBody final GroupChangeReqDto requestDto) {
        changeGroupService.execute(requestDto);
        return SuccessResponse.ok(null);
    }
}
