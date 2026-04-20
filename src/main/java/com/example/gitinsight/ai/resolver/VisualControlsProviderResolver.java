package com.example.gitinsight.ai.resolver;

import com.example.gitinsight.ai.client.VisualControlsProviderClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisualControlsProviderResolver {
    private final List<VisualControlsProviderClient> providers;

    public VisualControlsProviderResolver(List<VisualControlsProviderClient> providers) {
        this.providers = providers;
    }

    public VisualControlsProviderClient resolve(String url) {
        return providers.stream()
                .filter(provider -> provider.supports(url))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No provider found for URL: " + url));
    }
}
