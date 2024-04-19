package com.challenge.assembly.adapter;

import com.challenge.assembly.api.adapter.VotingSessionAdapter;
import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.domain.VotingSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VotingSessionAdapterTest {

    @InjectMocks
    private VotingSessionAdapter votingSessionAdapter;

    @Test
    void shouldConvertToResponse() {
        var votingSession = mock(VotingSession.class);
        var uuid = UUID.randomUUID();
        var issueUuid = UUID.randomUUID();
        var issue = mock(Issue.class);
        var now = new Date();

        given(votingSession.getId()).willReturn(uuid);
        given(votingSession.getIssue()).willReturn(issue);
        given(votingSession.getIssue().getId()).willReturn(issueUuid);
        given(votingSession.getCreationTime()).willReturn(now);
        given(votingSession.getExpirationTime()).willReturn(now);

        var response = votingSessionAdapter.toResponse(votingSession);

        assertThat(response.id()).isEqualTo(uuid);
        assertThat(response.issueId()).isEqualTo(issueUuid);
        assertThat(response.start()).isEqualTo(now);
        assertThat(response.end()).isEqualTo(now);
    }

    @Test
    void shouldConvertToDomain() {
        var issue = mock(Issue.class);
        var expirationTime = new Date();

        var votingSession = votingSessionAdapter.toDomain(issue, expirationTime);

        assertThat(votingSession.getIssue()).isEqualTo(issue);
        assertThat(votingSession.getExpirationTime()).isEqualTo(expirationTime);
    }
}
