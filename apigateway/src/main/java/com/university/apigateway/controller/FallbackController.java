package com.university.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @GetMapping("/contactSupport")
    public Mono<String> serviceFallback() {
        return Mono.just("Something went wrong. Please try again later.");
    }
}
