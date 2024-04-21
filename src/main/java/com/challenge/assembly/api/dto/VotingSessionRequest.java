package com.challenge.assembly.api.dto;

import java.time.LocalDateTime;

public record VotingSessionRequest(LocalDateTime expirationTime) {
}
