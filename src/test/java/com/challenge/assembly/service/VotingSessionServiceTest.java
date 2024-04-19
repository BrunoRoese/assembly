package com.challenge.assembly.service;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.repository.VotingSessionPageRepository;
import com.challenge.assembly.api.repository.VotingSessionRepository;
import com.challenge.assembly.api.service.VotingSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceTest {

    @Mock
    private VotingSessionPageRepository votingSessionPageRepository;
    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private VotingSessionService votingSessionService;

    @Test
    void shouldPageVotingSessions() {
        var page = 0;
        var size = 10;
        var pageRequest = PageRequest.of(page, size);
        var votingSessionPage = new PageImpl<>(List.of(new VotingSession()));

        given(votingSessionPageRepository.findAll(pageRequest)).willReturn(votingSessionPage);

        var response = votingSessionService.pageVotingSessions(page, size);

        verify(votingSessionPageRepository).findAll(pageRequest);
        assertThat(response).isEqualTo(votingSessionPage);
    }

    @Test
    void shouldSaveVotingSession() {
        var votingSession = new VotingSession();

        given(votingSessionRepository.save(votingSession)).willReturn(votingSession);

        var response = votingSessionService.saveVotingSession(votingSession);

        verify(votingSessionRepository).save(votingSession);
        assertThat(response).isEqualTo(votingSession);
    }
}
