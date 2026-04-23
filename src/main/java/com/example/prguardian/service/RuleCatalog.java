package com.example.prguardian.service;

import com.example.prguardian.model.ProgrammingLanguage;
import com.example.prguardian.model.RuleDefinition;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RuleCatalog {

    private final JsonRuleLoader jsonRuleLoader;

    private Map<ProgrammingLanguage, List<RuleDefinition>> rulesByLanguage;

    @PostConstruct
    public void init() {
        this.rulesByLanguage = jsonRuleLoader.loadRules()
                .stream()
                .filter(RuleDefinition::enabled)
                .collect(Collectors.groupingBy(RuleDefinition::language));
    }

    public List<RuleDefinition> getRulesFor(ProgrammingLanguage language) {
        return rulesByLanguage.getOrDefault(language, List.of());
    }

    public List<RuleDefinition> getAllRules() {
        return rulesByLanguage.values()
                .stream()
                .flatMap(List::stream)
                .toList();
    }
}
