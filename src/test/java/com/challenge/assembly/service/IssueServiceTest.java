package com.challenge.assembly.service;

import com.challenge.assembly.api.domain.Issue;
import com.challenge.assembly.api.exception.ConflictException;
import com.challenge.assembly.api.repository.IssuePageRepository;
import com.challenge.assembly.api.repository.IssueRepository;
import com.challenge.assembly.api.service.IssueService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {

    @Mock
    private IssuePageRepository issuePageRepository;
    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @Test
    void shouldGetPageOfIssues() {
        var page = 0;
        var size = 10;
        var pageRequest = PageRequest.of(page, size);
        var issues = List.of(mock(Issue.class));

        given(issuePageRepository.findAll(pageRequest)).willReturn(new PageImpl<>(issues));

        var response = issueService.getIssues(page, size);

        verify(issuePageRepository).findAll(pageRequest);
        assertThat(response.getContent()).isEqualTo(issues);
    }

    @Test
    void shouldSaveIssue() {
        var issue = mock(Issue.class);
        var savedIssue = mock(Issue.class);

        given(issueRepository.save(issue)).willReturn(savedIssue);

        var response = issueService.createIssue(issue);

        verify(issueRepository).save(issue);
        assertThat(response).isEqualTo(savedIssue);
    }

    @Nested
    class getIssueById {
        @Test
        void shouldGetIssueById() {
            var issue = mock(Issue.class);
            var uuid = UUID.randomUUID();

            given(issueRepository.findById(uuid)).willReturn(Optional.of(issue));

            var response = issueService.getIssueById(uuid);

            verify(issueRepository).findById(uuid);
            assertThat(response).isEqualTo(issue);
        }

        @Test
        void shouldThrowConflictWhenIssueIdDoesntExist() {
            var uuid = UUID.randomUUID();

            given(issueRepository.findById(uuid)).willReturn(Optional.empty());

            assertThrows(ConflictException.class, () -> issueService.getIssueById(uuid));
        }
    }
}
