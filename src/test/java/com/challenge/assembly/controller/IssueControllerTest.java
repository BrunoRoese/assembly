package com.challenge.assembly.controller;

import com.challenge.assembly.api.adapter.IssueAdapter;
import com.challenge.assembly.api.controller.IssueController;
import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.dto.IssueRequest;
import com.challenge.assembly.api.service.IssueService;
import com.challenge.assembly.api.validation.IssueRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;
    @Mock
    private IssueAdapter issueAdapter;
    @Mock
    private IssueRequestValidator issueRequestValidator;

    @InjectMocks
    private IssueController issueController;

    @Test
    public void shouldGetIssues() {
        var page = 0;
        var size = 10;
        var issues = mock(Issue.class);
        var pageIssues = new PageImpl<>(List.of(issues));

        given(issueService.getIssues(page, size)).willReturn(pageIssues);

        var response = issueController.getIssues(page, size);

        assertThat(response).isEqualTo(pageIssues);
    }

    @Test
    void shouldCreateIssue() {
        var issueRequest = mock(IssueRequest.class);
        var issueDomain = mock(Issue.class);

        given(issueAdapter.toDomain(issueRequest)).willReturn(issueDomain);
        given(issueService.createIssue(issueDomain)).willReturn(issueDomain);

        var response = issueController.createIssue(issueRequest);

        verify(issueRequestValidator).validateIssueRequest(issueRequest);
        assertThat(response).isEqualTo(issueDomain);
    }
}
