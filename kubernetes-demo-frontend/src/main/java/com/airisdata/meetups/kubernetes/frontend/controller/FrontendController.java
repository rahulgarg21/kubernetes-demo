package com.airisdata.meetups.kubernetes.frontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/frontend")
@Api(value = "Frontend Rest API")
@Slf4j
public class FrontendController {

    private static final ParameterizedTypeReference<List<String>> LIST_PARAMETERIZED_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final String appInstanceId;
    private final WebClient backendWebClient;

    public FrontendController(final String appInstanceId, final WebClient backendWebClient) {
        this.appInstanceId = appInstanceId;
        this.backendWebClient = backendWebClient;
    }

    @GetMapping(value = "/route", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Routes the request to backend and provides instance ids of routed instances")
    public Mono<Map<String, List<String>>> route() {

        log.debug("Received frontend Request");

        return backendWebClient.post()
                .body(Mono.just(List.of(appInstanceId)), LIST_PARAMETERIZED_TYPE_REFERENCE)
                .retrieve()
                .bodyToMono(LIST_PARAMETERIZED_TYPE_REFERENCE)
                .map(instanceIds -> Map.of("routedInstances", instanceIds));
    }


}
