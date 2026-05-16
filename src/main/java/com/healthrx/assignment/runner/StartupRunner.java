package com.healthrx.assignment.runner;

import com.healthrx.assignment.dto.TestWebhookRequest;
import com.healthrx.assignment.dto.WebhookRequest;
import com.healthrx.assignment.service.HiringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final HiringService hiringService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting automation flow...");

        WebhookRequest webhookRequest = WebhookRequest.builder()
                .name("Ishika Upadhyay")
                .regNo("0827CD231039")
                .email("ishikaupadhyay230142@acropolis.in")
                .build();

        hiringService.generateWebhook(webhookRequest)
                .flatMap(response -> {
                    String accessToken = response.getAccessToken();
                    // regNo ends with 39 (odd), Question 1 applies
                    String finalQuery = "SELECT 1";
                    
                    TestWebhookRequest testRequest = TestWebhookRequest.builder()
                            .finalQuery(finalQuery)
                            .build();
                    
                    return hiringService.testWebhook(accessToken, testRequest);
                })
                .subscribe(
                    result -> log.info("Automation flow completed successfully."),
                    error -> log.error("Automation flow failed: {}", error.getMessage())
                );
    }
}
