package com.challenge.assembly.api.adapter;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.dto.VotingSessionResponse;
import org.springframework.stereotype.Component;

@Component
public class VotingSessionAdapter {

    public VotingSessionResponse toResponse(VotingSession votingSession) {
        return new VotingSessionResponse(
            votingSession.getId(),
            votingSession.getIssue().getId(),
            votingSession.getCreationTime(),
            votingSession.getExpirationTime()
        );
    }
}
