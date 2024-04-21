package com.challenge.assembly.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VotingSessionResponse(UUID id, UUID issueId, LocalDateTime start, LocalDateTime end) {
}
