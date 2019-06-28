package com.airisdata.meetups.kubernetes.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2WebFlux
public class BackendSwaggerConfig {

    @Bean
    public Docket apiDocker() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kubernetes-backend")
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.ant("/api/v1/backend/**"))
                .build();
    }

    private static ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Kubernetes Demo Backend")
                .description("RESTful API for K8S Backend Demo")
                .version("1.0")
                .build();
    }


}
