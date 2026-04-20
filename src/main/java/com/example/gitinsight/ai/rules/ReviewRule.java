package com.example.gitinsight.ai.rules;

import com.example.gitinsight.ai.model.RuleContext;
import com.example.gitinsight.ai.model.RuleFinding;

import java.util.List;

public interface ReviewRule {

    String getName();

    boolean supports(RuleContext context);

    List<RuleFinding> evaluate(RuleContext context);
}