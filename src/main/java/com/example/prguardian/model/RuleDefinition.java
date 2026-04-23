package com.example.prguardian.model;

import java.util.List;

public record RuleDefinition(
        String id,
        String name,
        ProgrammingLanguage language,
        boolean enabled,
        String severity,
        String type,
        String scope,
        String message,
        List<RuleCondition> conditions
) {}