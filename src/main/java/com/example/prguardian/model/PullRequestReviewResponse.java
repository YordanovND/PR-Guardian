package com.example.prguardian.model;

import java.util.List;

public record PullRequestReviewResponse(
        String pullRequestUrl,
        int filesReviewed,
        int issuesFound,
        List<FileReviewResult> fileResults
) {
}