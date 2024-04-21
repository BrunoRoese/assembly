package com.challenge.assembly.api.service;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class VotingSessionExpirationTimeService {

    public LocalDateTime calculateExpirationTime(LocalDateTime expirationTime) {
        if (expirationTime == null) {
            return LocalDateTime.now().plusMinutes(1);
        }

        return expirationTime;
    }
}
