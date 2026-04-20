package com.example.gitinsight.ai.client;

import com.example.gitinsight.ai.config.GitHubProperties;
import com.example.gitinsight.ai.model.GitHubPullRequestFileDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GitHubRestClient extends BaseApiClient {

    public GitHubRestClient(BaseApiClientFactory factory, GitHubProperties properties) {
        super(factory.create(
                properties.baseUrl(),
                properties.token(),
                Map.of("Accept", "application/vnd.github+json")
        ));
    }

    public GitHubPullRequestFileDto[] getPullRequestFiles(String owner, String repo, String pullRequestId) {
        return get(
                "/repos/{owner}/{repo}/pulls/{pullRequestId}/files",
                GitHubPullRequestFileDto[].class,
                owner, repo, pullRequestId
        );
    }
}
