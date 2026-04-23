package com.example.prguardian.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubPullRequestFileDto(
        String filename,
        String status,
        Integer additions,
        Integer deletions,
        Integer changes,
        String patch,
        @JsonProperty("raw_url")
        String rawUrl,
        @JsonProperty("blob_url")
        String blobUrl
) {
}