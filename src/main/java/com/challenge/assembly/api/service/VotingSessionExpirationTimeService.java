package com.challenge.assembly.api.service;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotingSessionExpirationTimeService {

    public Date calculateExpirationTime(Date expirationTime) {
        if (expirationTime == null) {
            return DateUtils.addMinutes(new Date(), 1);
        }

        return expirationTime;
    }
}
