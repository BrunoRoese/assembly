package com.challenge.assembly.service;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.exception.NotFoundException;
import com.challenge.assembly.api.repository.VotingSessionPageRepository;
import com.challenge.assembly.api.repository.VotingSessionRepository;
import com.challenge.assembly.api.service.VotingSessionService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
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

    @Nested
    class GetVotingSessionById {
        private final UUID votingSessionId = UUID.randomUUID();

        @Test
        void shouldThrowConflictExceptionIfVotingSessionDoesntExist() {
            given(votingSessionRepository.findById(votingSessionId)).willReturn(Optional.empty());

            assertThrows(ConflictException.class, () -> votingSessionService.getVotingSessionById(votingSessionId));
        }

        @Test
        void shouldReturnVotingSessionIfIdExists() {
            var votingSession = mock(VotingSession.class);

            given(votingSessionRepository.findById(votingSessionId)).willReturn(Optional.of(votingSession));

            var response = votingSessionService.getVotingSessionById(votingSessionId);

            assertThat(response).isEqualTo(votingSession);
        }
    }
}
