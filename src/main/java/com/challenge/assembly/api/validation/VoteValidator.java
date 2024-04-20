package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class VoteValidator {
    public void validateVotingSessionId(String votingSessionId) {
        if (votingSessionId == null || votingSessionId.isEmpty()) {
            throw new BadRequestException("Voting session ID cannot be null or empty");
        }
    }
}
