package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public Page<Vote> getVotesBySession(
            @RequestParam(name = "votingSessionId") String votingSessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageRequest = PageRequest.of(page, size);

        return voteService.getVotesForSession(mapStringToUuid(votingSessionId), pageRequest);
    }
}
