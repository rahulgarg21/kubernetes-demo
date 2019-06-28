package com.airisdata.meetups.kubernetes.frontend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Configuration
@Slf4j
public class FrontendConfig {

    @Bean
    public String appInstanceId(@Value("${spring.application.name}") final String applicationName,
                                @Value("${app-instance-id:}") final String appInstanceId) {
        final String applicationInstanceId = appInstanceId.isEmpty() ?
                (applicationName + "-" + new Random().nextInt(1000)) : appInstanceId;
        log.info("Assigning app Instance id: {}", applicationInstanceId);
        return applicationInstanceId;
    }

    @Bean
    public String backendRouteUrl(@Value("${backend-route-url}") final String backendBaseUrl) {
        log.info("BackendRouteUrl: {}", backendBaseUrl);
        return backendBaseUrl;
    }

    @Bean
    public WebClient backendWebClient(final String backendRouteUrl) {
        // build web client
        return WebClient.builder()
                // set content-type header
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(backendRouteUrl)
                .build();
    }


}
