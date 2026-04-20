package com.example.gitinsight.ai.model;

public record ChangedFile(
        String filename,
        String patch,
        String status,
        Integer additions,
        Integer deletions,
        Integer changes
) {
    public boolean isReviewable() {
        return patch != null && filename != null && filename.endsWith(".java");
    }
}