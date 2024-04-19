package com.challenge.assembly.validation;

import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.service.IssueService;
import com.challenge.assembly.api.validation.VotingSessionCreationValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.challenge.assembly.api.utils.UuidUtils.convertUuid;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VotingSessionCreationValidatorTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private VotingSessionCreationValidator votingSessionCreationValidator;

    @Nested
    class InvalidRequest {
        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowBadRequestOnEmptySource(String issueId) {
            assertThrows(BadRequestException.class, () -> votingSessionCreationValidator.validateIssueId(issueId));
        }

        @Test
        void shouldThrowConflictOnIssueNotFound() {
            var issueId = "123e4567-e89b-12d3-a456-426614174000";

            given(issueService.getIssueById(convertUuid(issueId))).willReturn(Optional.empty());

            assertThrows(ConflictException.class, () -> votingSessionCreationValidator.validateIssueId(issueId));
        }
    }

    @Nested
    class ValidRequest {
        @Test
        void shouldNotThrowExceptionOnValidIssueId() {
            var issueId = "123e4567-e89b-12d3-a456-426614174000";
            var issue = mock(Issue.class);

            given(issueService.getIssueById(convertUuid(issueId))).willReturn(Optional.of(issue));

            assertDoesNotThrow(() -> votingSessionCreationValidator.validateIssueId(issueId));
        }
    }
}
