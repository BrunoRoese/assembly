package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.VoteStatus;
import com.challenge.assembly.api.dto.VotingSessionResult;
import com.challenge.assembly.api.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VotingSessionResultsService {

    private final VoteRepository voteRepository;
    private final VotingSessionService votingSessionService;

    public VotingSessionResult getResults(UUID votingSessionId) {
        var votingSession = votingSessionService.getVotingSessionById(votingSessionId);
        var yesVotes = voteRepository.countByVotingSessionIdAndStatus(votingSessionId, VoteStatus.YES);
        var noVotes = voteRepository.countByVotingSessionIdAndStatus(votingSessionId, VoteStatus.NO);
        var totalVotes = yesVotes + noVotes;
        var yesPercentage = calculatePercentage(yesVotes, totalVotes);
        var noPercentage = calculatePercentage(noVotes, totalVotes);
        var isActive = votingSessionService.isVotingSessionActive(votingSession);

        return new VotingSessionResult(
                totalVotes,
                yesVotes,
                noVotes,
                yesPercentage,
                noPercentage,
                isActive
        );
    }

    private BigDecimal calculatePercentage(Integer votes, Integer totalVotes) {
        var bigDecimalVotes = new BigDecimal(votes);
        var bigDecimalTotalVotes = new BigDecimal(totalVotes);

        return bigDecimalVotes.divide(bigDecimalTotalVotes, 4, RoundingMode.FLOOR)
                .multiply(new BigDecimal(100))
                .setScale(2, RoundingMode.FLOOR);
    }
}
