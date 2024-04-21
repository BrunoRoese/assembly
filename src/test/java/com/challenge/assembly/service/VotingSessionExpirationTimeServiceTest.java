package com.challenge.assembly.service;

import com.challenge.assembly.api.service.VotingSessionExpirationTimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VotingSessionExpirationTimeServiceTest {

    @InjectMocks
    private VotingSessionExpirationTimeService votingSessionExpirationTimeService;

    @Test
    void shouldCalculateExpirationTime() {
        var response = votingSessionExpirationTimeService.calculateExpirationTime(null);

        assertNotNull(response);
    }

    @Test
    void shouldReturnExpirationTime() {
        var expirationTime = LocalDateTime.now();

        var response = votingSessionExpirationTimeService.calculateExpirationTime(expirationTime);

        assertThat(response).isEqualTo(expirationTime);
    }
}
