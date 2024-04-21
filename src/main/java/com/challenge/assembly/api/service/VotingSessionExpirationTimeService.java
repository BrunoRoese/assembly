package com.challenge.assembly.api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingSessionExpirationTimeService {

    public LocalDateTime calculateExpirationTime(LocalDateTime expirationTime) {
        if (expirationTime == null) {
            return LocalDateTime.now().plusMinutes(1);
        }

        return expirationTime;
    }
}
