package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.adapter.VoteAdapter;
import com.challenge.assembly.api.dto.VoteResponse;
import com.challenge.assembly.api.service.VoteService;
import com.challenge.assembly.api.service.VotingSessionService;
import com.challenge.assembly.api.validation.VoteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VotingSessionService votingSessionService;
    private final VoteValidator voteValidator;
    private final VoteAdapter voteAdapter;

    @GetMapping
    public Page<VoteResponse> getVotesBySession(
            @RequestParam(name = "votingSessionId") String votingSessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        voteValidator.validateVotingSessionId(votingSessionId);

        var votingSessionUuid = mapStringToUuid(votingSessionId);

        var votingSession = votingSessionService.getVotingSessionById(votingSessionUuid);

        var pageRequest = PageRequest.of(page, size);

        return voteService.getVotesForSession(votingSession, pageRequest).map(voteAdapter::toResponse);
    }
}
