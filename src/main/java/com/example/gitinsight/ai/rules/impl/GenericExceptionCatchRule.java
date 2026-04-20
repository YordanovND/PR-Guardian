package com.example.gitinsight.ai.rules.impl;

import com.example.gitinsight.ai.model.RuleContext;
import com.example.gitinsight.ai.model.RuleFinding;
import com.example.gitinsight.ai.rules.ReviewRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.gitinsight.ai.model.FindingSeverity.MEDIUM;
import static com.example.gitinsight.ai.model.FindingType.CODE_SMELL;

@Component
public class GenericExceptionCatchRule implements ReviewRule {

    private static final Pattern PATTERN =
            Pattern.compile("catch\\s*\\(\\s*Exception\\s+\\w+\\s*\\)");

    @Override
    public String getName() {
        return "GENERIC_EXCEPTION_CATCH";
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
        Matcher matcher = PATTERN.matcher(patch);

        List<RuleFinding> findings = new ArrayList<>();

        while (matcher.find()) {
            findings.add(new RuleFinding(
                    getName(),
                    CODE_SMELL.name(),
                    MEDIUM,
                    context.changedFile().filename(),
                    null,
                    "Generic exception catch detected. Catch more specific exception types."
            ));
        }

        return findings;
    }
}