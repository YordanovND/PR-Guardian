package com.example.gitinsight.ai.model;

public enum VisualControlProvider {
    GITHUB("Github", "github.com");

    private final String name;
    private final String resolver;

    VisualControlProvider(String name, String resolver) {
        this.name = name;
        this.resolver = resolver;
    }


    public String getName() {
        return name;
    }

    public String getResolver() {
        return resolver;
    }
}
