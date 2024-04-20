package com.challenge.assembly.api.dto;

import com.challenge.assembly.api.domain.VoteStatus;

import java.util.UUID;

public record VoteResponse(UUID voteId, UUID userId, UUID votingSessionId, UUID issueId, VoteStatus voteStatus) {
}
