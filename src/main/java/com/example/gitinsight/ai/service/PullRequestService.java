package com.example.gitinsight.ai.service;

import com.example.gitinsight.ai.client.VisualControlsProviderClient;
import com.example.gitinsight.ai.model.ChangedFile;
import com.example.gitinsight.ai.model.PullRequestRef;
import com.example.gitinsight.ai.resolver.VisualControlsProviderResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PullRequestService {

    private final VisualControlsProviderResolver providerResolver;

    public PullRequestService(VisualControlsProviderResolver providerResolver) {
        this.providerResolver = providerResolver;
    }

    public List<ChangedFile> getReviewableFiles(String pullRequestUrl) {
        VisualControlsProviderClient provider = providerResolver.resolve(pullRequestUrl);
        PullRequestRef ref = provider.parsePullRequestUrl(pullRequestUrl);

        return provider.getChangedFiles(ref)
                .stream()
                .filter(ChangedFile::isReviewable)
                .toList();
    }
}
