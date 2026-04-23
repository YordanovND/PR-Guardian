package com.example.prguardian.model;

import java.util.List;

public record RuleCondition(
        String kind,
        String pattern
) {}