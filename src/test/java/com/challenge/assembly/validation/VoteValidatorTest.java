package com.challenge.assembly.validation;

import com.challenge.assembly.api.domain.VoteStatus;
import com.challenge.assembly.api.dto.VoteRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.validation.VoteValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VoteValidatorTest {

    @InjectMocks
    private VoteValidator voteValidator;

    @Nested
    class ValidateVotingSessionId {
        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowBadRequestIfVotingSessionIdIsNullOrEmpty(String votingSessionId) {
            assertThrows(BadRequestException.class, () -> voteValidator.validateVotingSessionId(votingSessionId));
        }

        @Test
        void shouldNotThrowExceptionIfVotingSessionIdExists() {
            assertDoesNotThrow(() -> voteValidator.validateVotingSessionId("test"));
        }
    }

    @Nested
    class ValidateVoteRequest {
        @ParameterizedTest
        @NullSource
        void shouldThrowBadRequestIfVoteRequestIsNull(VoteRequest voteRequest) {
            assertThrows(BadRequestException.class, () -> voteValidator.validateVoteRequest(voteRequest));
        }

        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowBadRequestIfVotingSessionIdIsEmpty(String votingSessionId) {
            var voteRequest = mock(VoteRequest.class);

            given(voteRequest.votingSessionId()).willReturn(votingSessionId);

            assertThrows(BadRequestException.class, () -> voteValidator.validateVoteRequest(voteRequest));
        }

        @ParameterizedTest
        @NullSource
        void shouldThrowBadRequestIfVoteStatusIsNull(VoteStatus voteStatus) {
            var voteRequest = mock(VoteRequest.class);

            given(voteRequest.votingSessionId()).willReturn("test");
            given(voteRequest.voteStatus()).willReturn(voteStatus);

            assertThrows(BadRequestException.class, () -> voteValidator.validateVoteRequest(voteRequest));
        }

        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowBadRequestIfUserIdIsEmpty(String userId) {
            var voteRequest = mock(VoteRequest.class);

            given(voteRequest.votingSessionId()).willReturn("test");
            given(voteRequest.voteStatus()).willReturn(VoteStatus.YES);
            given(voteRequest.userId()).willReturn(userId);

            assertThrows(BadRequestException.class, () -> voteValidator.validateVoteRequest(voteRequest));
        }

        @Test
        void shouldNotThrowExceptionIfVoteRequestIsValid() {
            var voteRequest = mock(VoteRequest.class);

            given(voteRequest.votingSessionId()).willReturn("test");
            given(voteRequest.voteStatus()).willReturn(VoteStatus.YES);
            given(voteRequest.userId()).willReturn("test");

            assertDoesNotThrow(() -> voteValidator.validateVoteRequest(voteRequest));
        }
    }
}
