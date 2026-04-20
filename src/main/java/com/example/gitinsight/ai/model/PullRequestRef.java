package com.example.gitinsight.ai.model;

public record PullRequestRef(
        String provider,
        String projectOwner,
        String projectName,
        String requestId
) {
}