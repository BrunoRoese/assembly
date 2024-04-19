package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.adapter.VotingSessionAdapter;
import com.challenge.assembly.api.dto.VotingSessionResponse;
import com.challenge.assembly.api.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/voting-sessions")
@RequiredArgsConstructor
public class VotingSessionController {

    private final VotingSessionService votingSessionService;
    private final VotingSessionAdapter votingSessionAdapter;

    @GetMapping
    public Page<VotingSessionResponse> getVotingSessions(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return votingSessionService.pageVotingSessions(page, size).map(votingSessionAdapter::toResponse);
    }
}
