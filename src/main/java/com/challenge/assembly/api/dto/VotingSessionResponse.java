package com.challenge.assembly.api.dto;

import java.util.Date;
import java.util.UUID;

public record VotingSessionResponse(UUID id, UUID issueId, Date start, Date end) {
}
