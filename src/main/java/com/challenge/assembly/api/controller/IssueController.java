package com.challenge.assembly.api.controller;

import com.challenge.assembly.api.adapter.IssueAdapter;
import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.dto.IssueRequest;
import com.challenge.assembly.api.service.IssueService;
import com.challenge.assembly.api.validation.IssueRequestValidator;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final IssueAdapter issueAdapter;
    private final IssueRequestValidator issueRequestValidator;

    @GetMapping
    public Page<Issue> getIssues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return issueService.getIssues(page, size);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Issue createIssue(@RequestBody IssueRequest issueRequest) {
        issueRequestValidator.validateIssueRequest(issueRequest);

        var issueDomain = issueAdapter.toDomain(issueRequest);

        return issueService.createIssue(issueDomain);
    }
}
