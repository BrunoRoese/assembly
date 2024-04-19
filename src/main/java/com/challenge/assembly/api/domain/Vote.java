package com.challenge.assembly.api.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    @ManyToOne
    @JoinColumn(name = "voting_session_id")
    private VotingSession votingSession;
}
