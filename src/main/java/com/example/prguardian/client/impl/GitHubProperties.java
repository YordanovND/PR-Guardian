package com.example.prguardian.client.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.github")
public record GitHubProperties(
        String baseUrl
) {
}
