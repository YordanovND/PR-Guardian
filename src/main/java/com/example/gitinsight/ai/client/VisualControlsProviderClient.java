package com.example.gitinsight.ai.client;

import com.example.gitinsight.ai.model.ChangedFile;
import com.example.gitinsight.ai.model.PullRequestRef;

import java.util.List;

public interface VisualControlsProviderClient {

    boolean supports(String url);

    PullRequestRef parsePullRequestUrl(String url);

    List<ChangedFile> getChangedFiles(PullRequestRef pullRequestRef);
}
