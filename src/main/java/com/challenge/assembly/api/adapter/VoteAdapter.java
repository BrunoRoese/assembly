package com.challenge.assembly.api.adapter;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.domain.VoteStatus;
import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.dto.VoteRequest;
import com.challenge.assembly.api.dto.VoteResponse;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import org.springframework.stereotype.Component;

import static com.challenge.assembly.api.mapper.UuidMapper.mapStringToUuid;

@Component
public class VoteAdapter {

    public VoteResponse toResponse(Vote vote) {
        return new VoteResponse(
                vote.getId(),
                vote.getUserId(),
                vote.getVotingSession().getId(),
                vote.getVotingSession().getIssue().getId(),
                vote.getStatus()
        );
    }

    public Vote toDomain(VoteRequest voteRequest, VotingSession votingSession) {
        try {
            var userId = mapStringToUuid(voteRequest.userId());

            return new Vote(userId, voteRequest.voteStatus(), votingSession);
        } catch (ConflictException e) {
            throw new BadRequestException("Invalid user UUID");
        }
    }
}
