package com.example.prguardian.service;

import com.example.prguardian.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonRuleEngine {

    private final RuleCatalog ruleCatalog;
    private final RuleMatcher ruleMatcher;

    public PullRequestReviewResponse review(String pullRequestUrl, List<ChangedFile> changedFiles) {
        List<FileReviewResult> fileResults = changedFiles.stream()
                .filter(ChangedFile::isReviewable)
                .map(this::reviewFile)
                .toList();

        int issuesFound = fileResults.stream()
                .mapToInt(file -> file.violations().size())
                .sum();

        return new PullRequestReviewResponse(
                pullRequestUrl,
                fileResults.size(),
                issuesFound,
                fileResults
        );
    }

    private FileReviewResult reviewFile(ChangedFile changedFile) {
        List<RuleViolation> violations = ruleCatalog.getRulesFor(changedFile.language()).stream()
                .flatMap(rule -> ruleMatcher.evaluate(rule, changedFile).stream())
                .toList();

        return new FileReviewResult(
                changedFile.filename(),
                changedFile.language(),
                violations
        );
    }
}