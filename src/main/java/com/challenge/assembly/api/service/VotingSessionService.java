package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.repository.VotingSessionPageRepository;
import com.challenge.assembly.api.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

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

    public VotingSession getVotingSessionByStringId(String votingSessionId) {
        var votingSessionUuid = mapStringToUuid(votingSessionId, "Invalid voting session UUID");

        return getVotingSessionById(votingSessionUuid);
    }

    public boolean isVotingSessionActive(VotingSession votingSession) {
        return votingSession.getExpirationTime().isAfter(LocalDateTime.now());
    }
}
