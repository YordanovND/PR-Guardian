package com.example.prguardian.model;

public record ChangedFile(
        String filename,
        String patch,
        String status,
        Integer additions,
        Integer deletions,
        Integer changes,
        ProgrammingLanguage language
) {
    public boolean isReviewable() {
        return patch != null && language != ProgrammingLanguage.UNKNOWN;
    }
}