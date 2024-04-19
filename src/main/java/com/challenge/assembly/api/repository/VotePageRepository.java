package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotePageRepository extends PagingAndSortingRepository<Vote, UUID> {

    @Query
    public Page<Vote> getVoteByVotingSessionId(UUID votingSessionId, PageRequest page);
}
