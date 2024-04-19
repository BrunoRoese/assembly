package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.repository.VotePageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VotePageRepository votePageRepository;

    public Page<Vote> getVotesForSession(VotingSession votingSession, PageRequest pageRequest) {
        return votePageRepository.getVoteByVotingSession(votingSession, pageRequest);
    }
}
