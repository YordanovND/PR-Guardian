package com.example.prguardian.model;

public record RuleViolation(
        String ruleId,
        String ruleName,
        String severity,
        String type,
        String message
) {
}