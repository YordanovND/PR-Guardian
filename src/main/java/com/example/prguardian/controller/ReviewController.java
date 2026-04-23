package com.example.prguardian.controller;

import com.example.prguardian.model.PullRequestReviewResponse;
import com.example.prguardian.service.PullRequestService;
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
        return service.review(url);
    }
}
