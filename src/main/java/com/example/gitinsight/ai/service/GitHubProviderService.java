package com.example.gitinsight.ai.service;

import com.example.gitinsight.ai.client.GitHubRestClient;
import com.example.gitinsight.ai.client.VisualControlsProviderClient;
import com.example.gitinsight.ai.model.ChangedFile;
import com.example.gitinsight.ai.model.GitHubPullRequestFileDto;
import com.example.gitinsight.ai.model.PullRequestRef;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.example.gitinsight.ai.model.VisualControlProvider.GITHUB;

@Service
@RequiredArgsConstructor
public class GitHubProviderService implements VisualControlsProviderClient {

    private final GitHubRestClient gitHubRestClient;

    @Override
    public boolean supports(String url) {
        return url != null && url.contains(GITHUB.getResolver());
    }

    @Override
    public PullRequestRef parsePullRequestUrl(String url) {
        URI uri = URI.create(url);
        String[] parts = uri.getPath().split("/");

        if (parts.length < 5 || !"pull".equals(parts[3])) {
            throw new IllegalArgumentException("Invalid GitHub pull request URL: " + url);
        }

        return new PullRequestRef(
                GITHUB.getName(),
                parts[1],
                parts[2],
                parts[4]
        );
    }

    @Override
    public List<ChangedFile> getChangedFiles(PullRequestRef ref) {
        GitHubPullRequestFileDto[] files = gitHubRestClient.getPullRequestFiles(
                ref.projectOwner(),
                ref.projectName(),
                ref.requestId()
        );

        if (files == null) {
            return List.of();
        }

        return Arrays.stream(files)
                .map(this::toChangedFile)
                .toList();
    }

    private ChangedFile toChangedFile(GitHubPullRequestFileDto dto) {
        return new ChangedFile(
                dto.filename(),
                dto.patch(),
                dto.status(),
                dto.additions(),
                dto.deletions(),
                dto.changes()
        );
    }
}
