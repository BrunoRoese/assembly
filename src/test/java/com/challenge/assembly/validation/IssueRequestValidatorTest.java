package com.challenge.assembly.validation;

import com.challenge.assembly.api.dto.IssueRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.validation.IssueRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class IssueRequestValidatorTest {

    @InjectMocks
    private IssueRequestValidator issueRequestValidator;

    @ParameterizedTest
    @EmptySource
    void shouldThrowBadRequestExceptionWhenTitleIsBlank(String title) {
        var issueRequest = mock(IssueRequest.class);

        given(issueRequest.title()).willReturn(title);

        assertThrows(BadRequestException.class, () -> issueRequestValidator.validateIssueRequest(issueRequest));
    }

    @Test
    void shouldNotThrowExceptionWhenTitleExists() {
        var issueRequest = mock(IssueRequest.class);

        given(issueRequest.title()).willReturn("Title");

        assertDoesNotThrow(() -> issueRequestValidator.validateIssueRequest(issueRequest));
    }
}
