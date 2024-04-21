package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.dto.VoteRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import org.springframework.stereotype.Component;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@Component
public class VoteValidator {
    public void validateVotingSessionId(String votingSessionId) {
        if (votingSessionId == null || votingSessionId.isEmpty()) {
            throw new BadRequestException("Voting session ID cannot be null or empty");
        }
    }

    public void validateVoteRequest(VoteRequest voteRequest) {
        if (voteRequest == null) {
            throw new BadRequestException("Vote request cannot be null");
        }

        if (voteRequest.votingSessionId() == null || voteRequest.votingSessionId().isEmpty()) {
            throw new BadRequestException("Voting session ID cannot be null or empty");
        }

        if (voteRequest.voteStatus() == null) {
            throw new BadRequestException("Vote status cannot be null");
        }

        if (voteRequest.userId() == null || voteRequest.userId().isEmpty()) {
            throw new BadRequestException("User ID cannot be null or empty");
        }
    }
}
