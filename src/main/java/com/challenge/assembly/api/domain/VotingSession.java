package com.challenge.assembly.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "voting_session")
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
    private LocalDateTime expirationTime;
    @CreationTimestamp
    private LocalDateTime creationTime;
}
