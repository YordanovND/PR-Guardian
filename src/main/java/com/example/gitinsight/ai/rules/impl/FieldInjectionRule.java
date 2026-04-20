package com.example.gitinsight.ai.rules.impl;

import com.example.gitinsight.ai.model.RuleContext;
import com.example.gitinsight.ai.model.RuleFinding;
import com.example.gitinsight.ai.rules.ReviewRule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.gitinsight.ai.model.FindingSeverity.MEDIUM;
import static com.example.gitinsight.ai.model.FindingType.SPRING_ANTI_PATTERN;

@Component
public class FieldInjectionRule implements ReviewRule {

    private static final Pattern AUTOWIRED_FIELD =
            Pattern.compile("@Autowired\\s+private");

    @Override
    public String getName() {
        return "FIELD_INJECTION";
    }

    @Override
    public boolean supports(RuleContext context) {
        return context.changedFile().filename() != null
                && context.changedFile().filename().endsWith(".java")
                && context.changedFile().patch() != null;
    }

    @Override
    public List<RuleFinding> evaluate(RuleContext context) {
        if (!AUTOWIRED_FIELD.matcher(context.changedFile().patch()).find()) {
            return List.of();
        }

        return List.of(
                new RuleFinding(
                        getName(),
                        SPRING_ANTI_PATTERN.name(),
                        MEDIUM,
                        context.changedFile().filename(),
                        null,
                        "Field injection detected. Prefer constructor injection."
                )
        );
    }
}