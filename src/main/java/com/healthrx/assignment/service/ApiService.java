package com.healthrx.assignment.service;

import com.healthrx.assignment.dto.TestWebhookRequest;
import com.healthrx.assignment.dto.WebhookRequest;
import com.healthrx.assignment.dto.WebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {

    private final WebClient webClient;
    private static final String BASE_URL = "https://bfhldevapigw.healthrx.co.in/hiring";

    public Mono<WebhookResponse> generateWebhook(WebhookRequest request) {
        log.info("Calling generateWebhook API with request: {}", request);
        return webClient.post()
                .uri(BASE_URL + "/generateWebhook/JAVA")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WebhookResponse.class)
                .doOnSuccess(response -> log.info("Webhook generated successfully: {}", response))
                .doOnError(error -> log.error("Error generating webhook: {}", error.getMessage()));
    }

    public Mono<String> testWebhook(String accessToken, TestWebhookRequest request) {
        log.info("Calling testWebhook API with accessToken: {} and query: {}", accessToken, request.getFinalQuery());
        return webClient.post()
                .uri(BASE_URL + "/testWebhook/JAVA")
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> log.info("Final query submitted successfully. Response: {}", response))
                .doOnError(error -> log.error("Error submitting final query: {}", error.getMessage()));
    }
}
