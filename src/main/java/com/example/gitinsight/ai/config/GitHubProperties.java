package com.example.gitinsight.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.github")
public record GitHubProperties(
        String baseUrl
) {
}
