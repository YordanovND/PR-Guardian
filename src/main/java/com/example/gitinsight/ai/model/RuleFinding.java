package com.example.gitinsight.ai.model;

public record RuleFinding(
        String ruleName,
        String type,
        FindingSeverity severity,
        String filename,
        Integer lineHint,
        String message
) {
}