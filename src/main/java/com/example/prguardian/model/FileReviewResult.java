package com.example.prguardian.model;

import java.util.List;

public record FileReviewResult(
        String fileName,
        ProgrammingLanguage language,
        List<RuleViolation> violations
) {
}