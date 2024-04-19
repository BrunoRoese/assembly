package com.challenge.assembly.api.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "voting_session")
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
    private Date expirationTime;
}
