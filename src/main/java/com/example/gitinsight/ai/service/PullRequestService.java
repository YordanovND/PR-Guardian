package com.example.gitinsight.ai.service;

import com.example.gitinsight.ai.client.VisualControlsProviderClient;
import com.example.gitinsight.ai.model.*;
import com.example.gitinsight.ai.resolver.VisualControlsProviderResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PullRequestService {

    private final VisualControlsProviderResolver providerResolver;
    private final RuleEngine ruleEngine;

    public PullRequestReviewResponse reviewPullRequest(String pullRequestUrl) {
        VisualControlsProviderClient provider = providerResolver.resolve(pullRequestUrl);
        PullRequestRef ref = provider.parsePullRequestUrl(pullRequestUrl);

        List<ChangedFile> changedFiles = provider.getChangedFiles(ref);

        List<FileReviewResult> fileResults = changedFiles.stream()
                .map(this::reviewFile)
                .toList();

        List<RuleFinding> allFindings = fileResults.stream()
                .flatMap(file -> file.findings().stream())
                .toList();

        Map<String, List<RuleFinding>> findingsBySeverity = groupBySeverity(allFindings);

        return new PullRequestReviewResponse(
                ref.provider(),
                ref.projectOwner(),
                ref.projectName(),
                ref.requestId(),
                findingsBySeverity,
                fileResults
        );
    }

    private FileReviewResult reviewFile(ChangedFile changedFile) {
        List<RuleFinding> findings = changedFile.isReviewable()
                ? ruleEngine.evaluate(new RuleContext(changedFile))
                : List.of();

        return new FileReviewResult(
                changedFile.filename(),
                changedFile.isReviewable(),
                changedFile.additions(),
                changedFile.deletions(),
                changedFile.changes(),
                findings
        );
    }

    private Map<String, List<RuleFinding>> groupBySeverity(List<RuleFinding> findings) {
        return Map.of(
                "HIGH", findings.stream().filter(f -> f.severity() == FindingSeverity.HIGH).toList(),
                "MEDIUM", findings.stream().filter(f -> f.severity() == FindingSeverity.MEDIUM).toList(),
                "LOW", findings.stream().filter(f -> f.severity() == FindingSeverity.LOW).toList()
        );
    }
}
