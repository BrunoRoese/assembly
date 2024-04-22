package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.domain.VotingSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoteRepository extends CrudRepository<Vote, UUID> {

    @Query
    public Optional<Vote> findByVotingSessionAndUserId(VotingSession votingSession, UUID userId);
}
