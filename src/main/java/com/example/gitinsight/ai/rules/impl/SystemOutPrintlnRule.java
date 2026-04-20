package com.example.gitinsight.ai.rules.impl;

import com.example.gitinsight.ai.model.RuleContext;
import com.example.gitinsight.ai.model.RuleFinding;
import com.example.gitinsight.ai.rules.ReviewRule;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.gitinsight.ai.model.FindingSeverity.LOW;
import static com.example.gitinsight.ai.model.FindingType.CODE_SMELL;

@Component
public class SystemOutPrintlnRule implements ReviewRule {

    @Override
    public String getName() {
        return "SYSTEM_OUT_PRINTLN";
    }

    @Override
    public boolean supports(RuleContext context) {
        return context.changedFile().filename() != null
                && context.changedFile().filename().endsWith(".java")
                && context.changedFile().patch() != null;
    }

    @Override
    public List<RuleFinding> evaluate(RuleContext context) {
        String patch = context.changedFile().patch();

        if (!patch.contains("System.out.println") || !patch.contains("System.out.print")) {
            return List.of();
        }

        return List.of(
                new RuleFinding(
                        getName(),
                        CODE_SMELL.name(),
                        LOW,
                        context.changedFile().filename(),
                        null,
                        "Avoid using System.out.println or System.out.print in application code. Prefer structured logging."
                )
        );
    }
}