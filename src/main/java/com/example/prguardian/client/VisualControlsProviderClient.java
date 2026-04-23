package com.example.prguardian.client;

import com.example.prguardian.model.ChangedFile;
import com.example.prguardian.model.PullRequestRef;

import java.util.List;

public interface VisualControlsProviderClient {

    boolean supports(String url);

    PullRequestRef parsePullRequestUrl(String url);

    List<ChangedFile> getChangedFiles(PullRequestRef pullRequestRef);
}
