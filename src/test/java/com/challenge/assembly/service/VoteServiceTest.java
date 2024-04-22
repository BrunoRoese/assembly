package com.challenge.assembly.service;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.repository.VotePageRepository;
import com.challenge.assembly.api.repository.VoteRepository;
import com.challenge.assembly.api.service.VoteService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock
    private VotePageRepository votePageRepository;
    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    void shouldGetVotesForSession() {
        var votingSession = mock(VotingSession.class);
        var pageRequest = PageRequest.of(0, 10);
        var voteList = List.of(mock(Vote.class));

        given(votePageRepository.getVoteByVotingSession(votingSession, pageRequest)).willReturn(new PageImpl<>(voteList));

        var response = voteService.getVotesForSession(votingSession, pageRequest);

        verify(votePageRepository).getVoteByVotingSession(votingSession, pageRequest);
        assertThat(response.getContent()).isEqualTo(voteList);
    }

    @Test
    void shouldSaveVote() {
        var vote = mock(Vote.class);
        var savedVote = mock(Vote.class);

        given(voteRepository.save(vote)).willReturn(savedVote);

        var response = voteService.saveVote(vote);

        verify(voteRepository).save(vote);
        assertThat(response).isEqualTo(savedVote);
    }

    @Test
    void shouldGetOptionalVote() {;
        var optionalVote = mock(Vote.class);
        var votingSession = mock(VotingSession.class);
        var userId = UUID.randomUUID();

        given(voteRepository.findByVotingSessionAndUserId(votingSession, userId)).willReturn(Optional.of(optionalVote));

        var response = voteService.getVoteByVotingSessionAndUserId(votingSession, userId);

        verify(voteRepository).findByVotingSessionAndUserId(votingSession, userId);
        assertThat(response).isEqualTo(Optional.of(optionalVote));
    }
}
