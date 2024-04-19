package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteRepository extends CrudRepository<Vote, UUID> {
}
