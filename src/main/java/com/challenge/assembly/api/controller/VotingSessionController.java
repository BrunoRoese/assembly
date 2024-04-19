package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.adapter.VotingSessionAdapter;
import com.challenge.assembly.api.dto.VotingSessionRequest;
import com.challenge.assembly.api.dto.VotingSessionResponse;
import com.challenge.assembly.api.service.IssueService;
import com.challenge.assembly.api.service.VotingSessionExpirationTimeService;
import com.challenge.assembly.api.service.VotingSessionService;
import com.challenge.assembly.api.validation.VotingSessionCreationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@RestController
@RequestMapping("api/v1/voting-sessions")
@RequiredArgsConstructor
public class VotingSessionController {

    private final VotingSessionService votingSessionService;
    private final VotingSessionAdapter votingSessionAdapter;
    private final VotingSessionCreationValidator votingSessionCreationValidator;
    private final IssueService issueService;
    private final VotingSessionExpirationTimeService expirationTimeService;

    @GetMapping
    public Page<VotingSessionResponse> getVotingSessions(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return votingSessionService.pageVotingSessions(page, size).map(votingSessionAdapter::toResponse);
    }

    @PostMapping
    public VotingSessionResponse createVotingSession(
            @RequestParam(value = "issueId") String issueId,
            @RequestBody VotingSessionRequest votingSessionRequest) {
        votingSessionCreationValidator.validateIssueId(issueId, votingSessionRequest);

        var issue = issueService.getIssueById(mapStringToUuid(issueId));

        var expirationTime = expirationTimeService.calculateExpirationTime(votingSessionRequest.expirationTime());

        var votingSessionDomain = votingSessionAdapter.toDomain(issue, expirationTime);

        var savedVotingSession = votingSessionService.saveVotingSession(votingSessionDomain);

        return votingSessionAdapter.toResponse(savedVotingSession);
    }
}
