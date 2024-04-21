package com.challenge.assembly.api.adapter;

import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.dto.VotingSessionResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    public VotingSession toDomain(Issue issue, LocalDateTime expirationTime) {
        var votingSession = new VotingSession();

        votingSession.setIssue(issue);
        votingSession.setExpirationTime(expirationTime);

        return votingSession;
    }
}
