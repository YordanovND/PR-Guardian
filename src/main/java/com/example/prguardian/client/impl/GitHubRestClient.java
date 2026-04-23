package com.example.prguardian.client.impl;

import com.example.prguardian.client.BaseApiClient;
import com.example.prguardian.client.BaseApiClientFactory;
import com.example.prguardian.model.GitHubPullRequestFileDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GitHubRestClient extends BaseApiClient {

    private static final String PR_PULL_FILES_URL = "/repos/{owner}/{repo}/pulls/{pullRequestId}/files";

    public GitHubRestClient(BaseApiClientFactory factory, GitHubProperties properties) {
        super(factory.create(
                properties.baseUrl(),
                null,
                Map.of("Accept", "application/vnd.github+json")
        ));
    }

    public GitHubPullRequestFileDto[] getPullRequestFiles(String owner, String repo, String pullRequestId) {
        return get(
                PR_PULL_FILES_URL,
                GitHubPullRequestFileDto[].class,
                owner, repo, pullRequestId
        );
    }
}
