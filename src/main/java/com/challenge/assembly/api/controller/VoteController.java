package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.adapter.VoteAdapter;
import com.challenge.assembly.api.dto.VoteRequest;
import com.challenge.assembly.api.dto.VoteResponse;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.service.VoteService;
import com.challenge.assembly.api.service.VotingSessionService;
import com.challenge.assembly.api.validation.UserVoteValidator;
import com.challenge.assembly.api.validation.VoteValidator;
import com.challenge.assembly.api.validation.VotingSessionExpirationValidator;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    private final VotingSessionExpirationValidator votingSessionExpirationValidator;
    private final UserVoteValidator userVoteValidator;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Invalid voting session id")
            }
    )
    @GetMapping
    public Page<VoteResponse> getVotesBySession(
            @RequestParam(name = "votingSessionId") String votingSessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        voteValidator.validateVotingSessionId(votingSessionId);

        try {
            var votingSessionUuid = mapStringToUuid(votingSessionId);

            var votingSession = votingSessionService.getVotingSessionById(votingSessionUuid);

            var pageRequest = PageRequest.of(page, size);

            return voteService.getVotesForSession(votingSession, pageRequest).map(voteAdapter::toResponse);
        } catch (Exception e) {
            throw new BadRequestException("Invalid voting session UUID");
        }
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Invalid vote request"),
                    @ApiResponse(responseCode = "409", description = "Voting session expired or user already voted"),
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VoteResponse vote(@RequestBody VoteRequest voteRequest) {
        voteValidator.validateVoteRequest(voteRequest);

        var votingSession = votingSessionService.getVotingSessionByStringId(voteRequest.votingSessionId());

        votingSessionExpirationValidator.validateExpiration(votingSession);

        var voteDomain = voteAdapter.toDomain(voteRequest, votingSession);

        var optionalVote = voteService.getVoteByVotingSessionAndUserId(voteDomain.getVotingSession(), voteDomain.getUserId());

        userVoteValidator.validateUserVote(optionalVote);

        var savedVote = voteService.saveVote(voteDomain);

        return voteAdapter.toResponse(savedVote);
    }
}
