package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.exception.ConflictException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserVoteValidator {

    public void validateUserVote(Optional<Vote> vote) {
        vote.ifPresent(v -> {
            throw new ConflictException("User already voted");
        });
    }
}
