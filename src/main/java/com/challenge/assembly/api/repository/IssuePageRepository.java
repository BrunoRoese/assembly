package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.Issue;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuePageRepository extends PagingAndSortingRepository<Issue, Integer> {
}
