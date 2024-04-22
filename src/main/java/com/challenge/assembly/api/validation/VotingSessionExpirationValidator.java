package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.exception.ConflictException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotingSessionExpirationValidator {

    public void validateExpiration(VotingSession votingSession) {
        if (votingSession.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new ConflictException("Voting session has expired");
        }
    }
}
