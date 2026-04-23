package com.example.prguardian.service;

import com.example.prguardian.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class RuleMatcher {

    public List<RuleViolation> evaluate(RuleDefinition rule, ChangedFile changedFile) {
        String patch = changedFile.patch();

        if (patch == null || patch.isBlank()) {
            return List.of();
        }

        for (RuleCondition condition : rule.conditions()) {
            if ("REGEX".equalsIgnoreCase(condition.kind())) {
                Pattern pattern = Pattern.compile(condition.pattern(), Pattern.MULTILINE);

                if (pattern.matcher(patch).find()) {
                    return List.of(new RuleViolation(
                            rule.id(),
                            rule.name(),
                            rule.severity(),
                            rule.type(),
                            rule.message()
                    ));
                }
            }
        }

        return List.of();
    }
}