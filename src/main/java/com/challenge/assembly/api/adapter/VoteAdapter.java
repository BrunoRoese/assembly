package com.challenge.assembly.api.adapter;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.dto.VoteResponse;
import org.springframework.stereotype.Component;

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
}
