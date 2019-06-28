package com.airisdata.meetups.kubernetes.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/backend")
@Api(value = "Backend Rest API")
@Slf4j
public class BackendController {

    private final String appInstanceId;

    public BackendController(final String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    @PostMapping(value = "/route", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Appends its own backend instance id to given list of routed instances")
    public Mono<List<String>> route(@RequestBody final List<String> existingRoutedInstances) {

        log.debug("Received backend Request");

        return Mono.just(Stream.concat(existingRoutedInstances.stream(), Stream.of(appInstanceId))
                .collect(Collectors.toList()));

    }

}
