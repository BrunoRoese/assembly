package com.challenge.assembly.api.dto;

import com.challenge.assembly.api.domain.VoteStatus;

public record VoteRequest(String votingSessionId, VoteStatus voteStatus, String userId) {
}
