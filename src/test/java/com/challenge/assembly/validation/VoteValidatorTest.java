package com.challenge.assembly.validation;

import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.validation.VoteValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VoteValidatorTest {

    @InjectMocks
    private VoteValidator voteValidator;

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
