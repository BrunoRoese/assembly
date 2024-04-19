package com.challenge.assembly.api.repository;

import com.challenge.assembly.api.domain.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer> {
}
