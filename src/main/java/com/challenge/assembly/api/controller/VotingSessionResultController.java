package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.dto.VotingSessionResult;
import com.challenge.assembly.api.service.VotingSessionResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@RestController
@RequestMapping("api/v1/voting-sessions/results")
@RequiredArgsConstructor
public class VotingSessionResultController {

    private final VotingSessionResultsService votingSessionResultsService;

    @GetMapping("/calculate")
    public VotingSessionResult calculateVotingSessionResult(@RequestParam(value = "votingSessionId") String votingSessionId) {
        var votingSessionUuid = mapStringToUuid(votingSessionId, "Invalid voting session ID");

        return votingSessionResultsService.getResults(votingSessionUuid);
    }
}
