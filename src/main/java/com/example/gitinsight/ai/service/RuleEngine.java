package com.example.gitinsight.ai.service;

import com.example.gitinsight.ai.model.RuleContext;
import com.example.gitinsight.ai.model.RuleFinding;
import com.example.gitinsight.ai.rules.ReviewRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleEngine {

    private final List<ReviewRule> rules;

    public List<RuleFinding> evaluate(RuleContext context) {
        return rules.stream()
                .filter(rule -> rule.supports(context))
                .flatMap(rule -> rule.evaluate(context).stream())
                .toList();
    }
}