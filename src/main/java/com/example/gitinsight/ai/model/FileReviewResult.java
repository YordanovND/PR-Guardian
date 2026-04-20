package com.example.gitinsight.ai.model;

import java.util.List;

public record FileReviewResult(
        String filename,
        boolean reviewable,
        int additions,
        int deletions,
        int changes,
        List<RuleFinding> findings
) {
}