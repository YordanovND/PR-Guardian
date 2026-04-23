package com.example.prguardian.model;

public record PullRequestRef(
        String provider,
        String projectOwner,
        String projectName,
        String requestId
) {
}