package com.example.gitinsight.ai.model;

import java.util.List;
import java.util.Map;

public record PullRequestReviewResponse(
        String provider,
        String projectOwner,
        String projectName,
        String pullRequestId,
        Map<String, List<RuleFinding>> findingsBySeverity,
        List<FileReviewResult> files
) {
}