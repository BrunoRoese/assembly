package com.challenge.assembly.validation;

import com.challenge.assembly.api.dto.VotingSessionRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.validation.VotingSessionCreationValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VotingSessionCreationValidatorTest {

    @InjectMocks
    private VotingSessionCreationValidator votingSessionCreationValidator;

    @Nested
    class InvalidRequest {
        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowBadRequestOnEmptySource(String issueId) {
            var votingSessionRequest = mock(VotingSessionRequest.class);

            assertThrows(BadRequestException.class, () -> votingSessionCreationValidator.validateIssueId(issueId, votingSessionRequest));
        }

        @Test
        void shouldThrowBadRequestOnExpirationTimeBeforeCurrentDate() {
            var issueId = "123e4567-e89b-12d3-a456-426614174000";
            var votingSessionRequest = mock(VotingSessionRequest.class);
            var yesterday = LocalDateTime.now().minusDays(1);

            given(votingSessionRequest.expirationTime()).willReturn(yesterday);

            assertThrows(BadRequestException.class, () -> votingSessionCreationValidator.validateIssueId(issueId, votingSessionRequest));
        }
    }

    @Nested
    class ValidRequest {
        @Test
        void shouldNotThrowExceptionOnValidIssueId() {
            var issueId = "123e4567-e89b-12d3-a456-426614174000";
            var votingSessionRequest = mock(VotingSessionRequest.class);
            var tomorrow = LocalDateTime.now().plusDays(1);

            given(votingSessionRequest.expirationTime()).willReturn(tomorrow);

            assertDoesNotThrow(() -> votingSessionCreationValidator.validateIssueId(issueId, votingSessionRequest));
        }
    }
}
