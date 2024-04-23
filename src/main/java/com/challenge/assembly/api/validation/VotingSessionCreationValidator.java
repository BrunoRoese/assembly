package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.dto.VotingSessionRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VotingSessionCreationValidator {

    public void validateIssueId(String issueId, VotingSessionRequest votingSessionRequest) {
        if (issueId == null || issueId.isBlank()) {
            throw new BadRequestException("Issue ID is required");
        }

        if (votingSessionRequest.expirationTime() == null) {
            return;
        }

        if (votingSessionRequest.expirationTime().isBefore(LocalDateTime.now())) {
            throw new ConflictException("Expiration time must be in the future");
        }
    }
}
