package com.example.prguardian.service;

import com.example.prguardian.client.VisualControlsProviderClient;
import com.example.prguardian.model.*;
import com.example.prguardian.resolver.VisualControlsProviderResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PullRequestService {

    private final VisualControlsProviderResolver providerResolver;
    private final JsonRuleEngine jsonRuleEngine;

    public PullRequestReviewResponse review(String pullRequestUrl) {
        VisualControlsProviderClient provider = providerResolver.resolve(pullRequestUrl);

        PullRequestRef ref = provider.parsePullRequestUrl(pullRequestUrl);

        List<ChangedFile> changedFiles = provider.getChangedFiles(ref);

        return jsonRuleEngine.review(pullRequestUrl, changedFiles);
    }
}
