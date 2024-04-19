package com.challenge.assembly.api.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int userId;
    @Enumerated(EnumType.STRING)
    private VoteStatus status;
    @ManyToOne
    @JoinColumn(name = "voting_session_id")
    private VotingSession votingSession;
}
