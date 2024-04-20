package com.challenge.assembly.adapter;

import com.challenge.assembly.api.adapter.VoteAdapter;
import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.domain.VoteStatus;
import com.challenge.assembly.api.domain.VotingSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class VoteAdapterTest {

    @InjectMocks
    private VoteAdapter voteAdapter;

    @Test
    void shouldConvertToVoteResponse() {
        var vote = mock(Vote.class);
        var uuid = UUID.randomUUID();
        var userId = UUID.randomUUID();
        var votingSessionId = UUID.randomUUID();
        var issueId = UUID.randomUUID();
        var votingSession = mock(VotingSession.class);
        var issue = mock(Issue.class);

        given(vote.getId()).willReturn(uuid);
        given(vote.getUserId()).willReturn(userId);
        given(vote.getVotingSession()).willReturn(votingSession);
        given(votingSession.getId()).willReturn(votingSessionId);
        given(votingSession.getIssue()).willReturn(issue);
        given(issue.getId()).willReturn(issueId);
        given(vote.getStatus()).willReturn(VoteStatus.YES);

        var response = voteAdapter.toResponse(vote);

        assertThat(response.voteId()).isEqualTo(uuid);
        assertThat(response.userId()).isEqualTo(userId);
        assertThat(response.votingSessionId()).isEqualTo(votingSessionId);
        assertThat(response.issueId()).isEqualTo(issueId);
        assertThat(response.voteStatus()).isEqualTo(VoteStatus.YES);
    }
}
