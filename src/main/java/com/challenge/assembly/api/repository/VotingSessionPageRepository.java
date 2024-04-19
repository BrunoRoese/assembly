package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.VotingSession;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotingSessionPageRepository extends PagingAndSortingRepository<VotingSession, UUID> {
}
