package com.example.gitinsight.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @GetMapping(path = "/review")
    public String getReview() {
        return "Hello World!";
    }
}
