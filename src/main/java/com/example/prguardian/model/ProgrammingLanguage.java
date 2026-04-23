package com.example.prguardian.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum ProgrammingLanguage {
    JAVA(List.of("java")),
    JAVASCRIPT(List.of("js", "jsx", "mjs")),
    TYPESCRIPT(List.of("ts", "tsx")),
    PYTHON(List.of("py")),
    GO(List.of("go")),
    UNKNOWN(List.of(""));

    private final List<String> extensions;

    ProgrammingLanguage(List<String> extension) {
        this.extensions = extension;
    }

    public static ProgrammingLanguage fromExtension(String extension) {
        if (extension == null || extension.isBlank()) {
            return UNKNOWN;
        }

        ProgrammingLanguage[] values = values();
        for (ProgrammingLanguage lang : values) {
            if (lang.extensions.contains(extension)) {
                return lang;
            }
        }

        return UNKNOWN;
    }

}