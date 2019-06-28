package com.airisdata.meetups.kubernetes.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.Random;

@Configuration
@Slf4j
public class BackendConfig {

    @Bean
    public String appInstanceId(@Value("${spring.application.name}") final String applicationName,
                                @Value("${app-instance-id:}") final String appInstanceId) {
        final String applicationInstanceId = appInstanceId.isEmpty() ?
                (applicationName + "-" + new Random().nextInt(1000)) : appInstanceId;
        log.info("Assigning app Instance id: {}", applicationInstanceId);
        return applicationInstanceId;
    }


}
