package com.challenge.assembly.validation;

import com.challenge.assembly.api.domain.Vote;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.validation.UserVoteValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserVoteValidatorTest {

    @InjectMocks
    private UserVoteValidator userVoteValidator;

    @Nested
    class ValidateUserVote {
        @Test
        void shouldThrowConflictExceptionWhenVoteIsPresent() {
            assertThrows(ConflictException.class, () -> userVoteValidator.validateUserVote(Optional.of(mock(Vote.class))));
        }

        @Test
        void shouldNotThrowExceptionWhenVoteIsNotPresent() {
            assertDoesNotThrow(() -> userVoteValidator.validateUserVote(Optional.empty()));
        }
    }
}
