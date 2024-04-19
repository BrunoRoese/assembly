package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.dto.IssueRequest;
import com.challenge.assembly.api.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class IssueRequestValidator {

    public void validateIssueRequest(IssueRequest issueRequest) {
        if (issueRequest.getTitle().isBlank()) {
            throw new BadRequestException("Title is required");
        }
    }
}