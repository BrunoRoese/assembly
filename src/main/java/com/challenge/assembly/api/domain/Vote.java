package com.challenge.assembly.api.domain;

import com.challenge.assembly.api.converter.VoteStatusConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    @Column(name = "vote_status")
    @Convert(converter = VoteStatusConverter.class)
    private VoteStatus status;
    @ManyToOne
    @JoinColumn(name = "voting_session_id")
    private VotingSession votingSession;
}
