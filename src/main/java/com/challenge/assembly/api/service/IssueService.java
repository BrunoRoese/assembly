package com.challenge.assembly.api.service;

import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.repository.IssuePageRepository;
import com.challenge.assembly.api.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssuePageRepository issuePageRepository;
    private final IssueRepository issueRepository;


    public Page<Issue> getIssues(int page, int size) {
        var pageable = PageRequest.of(page, size);

        return issuePageRepository.findAll(pageable);
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }
}
