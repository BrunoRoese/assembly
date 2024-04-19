package com.challenge.assembly.controller;

import com.challenge.assembly.api.adapter.VotingSessionAdapter;
import com.challenge.assembly.api.controller.VotingSessionController;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.dto.VotingSessionResponse;
import com.challenge.assembly.api.service.VotingSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VotingSessionControllerTest {

    @Mock
    private VotingSessionService votingSessionService;
    @Mock
    private VotingSessionAdapter votingSessionAdapter;

    @InjectMocks
    private VotingSessionController votingSessionController;

    @Test
    void shouldGetVotingSessions() {
        var page = 0;
        var size = 10;
        var votingSession = mock(VotingSession.class);
        var votingSessionResponsePage = new PageImpl<>(List.of(votingSession));
        var votingSessionResponse = mock(VotingSessionResponse.class);

        given(votingSessionService.pageVotingSessions(page, size)).willReturn(votingSessionResponsePage);
        given(votingSessionAdapter.toResponse(votingSession)).willReturn(votingSessionResponse);

        var response = votingSessionController.getVotingSessions(page, size);

        assertThat(response).containsExactly(votingSessionResponse);
    }
}
