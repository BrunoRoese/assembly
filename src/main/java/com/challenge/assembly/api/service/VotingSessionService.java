package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.exception.NotFoundException;
import com.challenge.assembly.api.repository.VotingSessionPageRepository;
import com.challenge.assembly.api.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VotingSessionService {

    private final VotingSessionPageRepository votingSessionPageRepository;
    private final VotingSessionRepository votingSessionRepository;

    public Page<VotingSession> pageVotingSessions(int page, int size) {
        var pageRequest = PageRequest.of(page, size);

        return votingSessionPageRepository.findAll(pageRequest);
    }

    public VotingSession saveVotingSession(VotingSession votingSession) {
        return votingSessionRepository.save(votingSession);
    }

    public VotingSession getVotingSessionById(UUID votingSessionId) {
        return votingSessionRepository.findById(votingSessionId)
                .orElseThrow(() -> new ConflictException("Voting session not found"));
    }
}
