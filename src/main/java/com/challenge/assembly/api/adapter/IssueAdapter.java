package com.challenge.assembly.api.adapter;

import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.dto.IssueRequest;
import org.springframework.stereotype.Component;

@Component
public class IssueAdapter {
    public Issue toDomain(IssueRequest issueRequest) {
        var issue = new Issue();

        issue.setTitle(issueRequest.getTitle());

        return issue;
    }
}
