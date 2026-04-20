package com.example.gitinsight.ai.controller;

import com.example.gitinsight.ai.service.PullRequestService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ReviewController {

    private final PullRequestService service;

    public ReviewController(PullRequestService service) {
        this.service = service;
    }

    @GetMapping(path = "/review")
    public String getReview() {
        // service.getReviewableFiles();

        return "Hello World!";
    }
}
