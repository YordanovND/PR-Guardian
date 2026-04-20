package com.example.gitinsight.ai.controller;

import com.example.gitinsight.ai.model.PullRequestReviewResponse;
import com.example.gitinsight.ai.service.PullRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final PullRequestService service;

    @GetMapping
    public PullRequestReviewResponse getReview(@RequestParam String url) {
        PullRequestReviewResponse requestReviewResponse;
        try {
          requestReviewResponse = service.reviewPullRequest(url);
        } catch (Exception e) {
           throw new IllegalArgumentException("");
        }

        return requestReviewResponse;
    }

    @GetMapping(path = "/test")
    public String getTesting() {
        // service.getReviewableFiles();

        return "Hello World!";
    }
}
