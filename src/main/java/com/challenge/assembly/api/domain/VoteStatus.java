package com.challenge.assembly.api.domain;

import lombok.Getter;

@Getter
public enum VoteStatus {
    YES("yes"),
    NO("no");

    private final String status;

    VoteStatus(String status) {
        this.status = status;
    }
}
