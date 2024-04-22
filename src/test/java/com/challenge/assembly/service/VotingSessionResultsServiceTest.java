package com.challenge.assembly.service;

import com.challenge.assembly.api.domain.VoteStatus;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.repository.VoteRepository;
import com.challenge.assembly.api.service.VotingSessionResultsService;
import com.challenge.assembly.api.service.VotingSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class VotingSessionResultsServiceTest {

    @Mock
    private VoteRepository voteRepository;
    @Mock
    private VotingSessionService votingSessionService;

    @InjectMocks
    private VotingSessionResultsService votingSessionResultsService;

    @Test
    void getResults() {
        var votingSessionId = UUID.randomUUID();
        var votingSession = mock(VotingSession.class);
        var yesVotes = 1;
        var noVotes = 1;

        given(votingSessionService.getVotingSessionById(votingSessionId)).willReturn(votingSession);
        given(voteRepository.countByVotingSessionIdAndStatus(votingSessionId, VoteStatus.YES)).willReturn(yesVotes);
        given(voteRepository.countByVotingSessionIdAndStatus(votingSessionId, VoteStatus.NO)).willReturn(noVotes);
        given(votingSessionService.isVotingSessionActive(votingSession)).willReturn(true);

        var result = votingSessionResultsService.getResults(votingSessionId);

        assertThat(result).isNotNull();
        assertThat(result.totalVotes()).isEqualTo(2);
        assertThat(result.yesVotes()).isEqualTo(1);
        assertThat(result.noVotes()).isEqualTo(1);
        assertThat(result.yesPercentage()).isEqualTo(new BigDecimal("50.00"));
        assertThat(result.noPercentage()).isEqualTo(new BigDecimal("50.00"));
        assertThat(result.isActive()).isTrue();
    }
}
