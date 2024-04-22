package com.challenge.assembly.api.dto;

import java.math.BigDecimal;

public record VotingSessionResultResponse(Integer yesVotes, Integer noVotes, BigDecimal yesPercentage, BigDecimal noPercentage, boolean isActive) {
}
