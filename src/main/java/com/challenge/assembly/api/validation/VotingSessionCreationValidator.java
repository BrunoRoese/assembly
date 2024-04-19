package com.challenge.assembly.api.validation;

import com.challenge.assembly.api.exception.BadRequestException;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.challenge.assembly.api.utils.UuidUtils.convertUuid;

@Component
@RequiredArgsConstructor
public class VotingSessionCreationValidator {

    private final IssueService issueService;

    public void validateIssueId(String issueId) {
        if (issueId == null || issueId.isBlank()) {
            throw new BadRequestException("Issue ID is required");
        }

        if (issueService.getIssueById(convertUuid(issueId)).isEmpty()) {
            throw new ConflictException("Issue not found");
        }
    }
}
