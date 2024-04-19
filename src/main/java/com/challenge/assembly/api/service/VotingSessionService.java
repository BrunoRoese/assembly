package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.repository.VotingSessionPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingSessionService {

    private final VotingSessionPageRepository votingSessionPageRepository;

    public Page<VotingSession> pageVotingSessions(int page, int size) {
        var pageRequest = PageRequest.of(page, size);

        return votingSessionPageRepository.findAll(pageRequest);
    }
}
