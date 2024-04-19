package com.challenge.assembly.adapter;

import com.challenge.assembly.api.adapter.IssueAdapter;
import com.challenge.assembly.api.dto.IssueRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class IssueAdapterTest {

    @InjectMocks
    private IssueAdapter issueAdapter;

    @Test
    void shouldCreateDomain() {
        var issueRequest = mock(IssueRequest.class);

        given(issueRequest.title()).willReturn("Title");

        var response = issueAdapter.toDomain(issueRequest);

        assertThat(response.getTitle()).isEqualTo("Title");
    }
}
