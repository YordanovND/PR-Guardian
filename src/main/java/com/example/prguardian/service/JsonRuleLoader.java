package com.example.prguardian.service;

import com.example.prguardian.model.RuleDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonRuleLoader {

    private static final String RULES_LOCATION = "classpath*:rules/**/*.json";

    private final ObjectMapper objectMapper;

    public List<RuleDefinition> loadRules() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<RuleDefinition> rules = new ArrayList<>();

        try {
            Resource[] resources = resolver.getResources(RULES_LOCATION);

            for (Resource resource : resources) {
                try (InputStream inputStream = resource.getInputStream()) {
                    RuleDefinition rule = objectMapper.readValue(inputStream, RuleDefinition.class);
                    validate(rule, resource);
                    rules.add(rule);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load rule files from " + RULES_LOCATION, e);
        }

        return rules;
    }

    private void validate(RuleDefinition rule, Resource resource) {
        if (rule.id() == null || rule.id().isBlank()) {
            throw new IllegalStateException("Rule id is missing in file: " + safeName(resource));
        }

        if (rule.language() == null) {
            throw new IllegalStateException("Rule language is missing in file: " + safeName(resource));
        }

        if (rule.conditions() == null || rule.conditions().isEmpty()) {
            throw new IllegalStateException("Rule conditions are missing in file: " + safeName(resource));
        }
    }

    private String safeName(Resource resource) {
        try {
            return resource.getURI().toString();
        } catch (IOException e) {
            return resource.getFilename();
        }
    }
}