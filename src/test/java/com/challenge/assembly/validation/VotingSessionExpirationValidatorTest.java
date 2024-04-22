package com.challenge.assembly.validation;

import com.challenge.assembly.api.domain.VotingSession;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.validation.VotingSessionExpirationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VotingSessionExpirationValidatorTest {

    @InjectMocks
    private VotingSessionExpirationValidator votingSessionExpirationValidator;

    @Nested
    class ValidateExpiration {
        private VotingSession votingSession;

        @BeforeEach
        void setup() {
            votingSession = mock(VotingSession.class);
        }

        @Test
        void shouldThrowConflictExceptionIfVotingSessionHasExpired() {
            given(votingSession.getExpirationTime()).willReturn(LocalDateTime.now().minusDays(1));

            assertThrows(ConflictException.class, () -> votingSessionExpirationValidator.validateExpiration(votingSession));
        }

        @Test
        void shouldNotThrowConflictExceptionIfExpirationTimeIsInTheFuture() {
            given(votingSession.getExpirationTime()).willReturn(LocalDateTime.now().plusDays(1));

            assertDoesNotThrow(() -> votingSessionExpirationValidator.validateExpiration(votingSession));
        }
    }
}
