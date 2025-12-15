package com.kolosov.learnjava_jc_spring.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Шаблон Spring MVC"
        )
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi jsonView() {
        return GroupedOpenApi.builder()
                .group("JsonView")
                .pathsToMatch("/api/jsonView/**")
                .build();
    }

    @Bean
    public GroupedOpenApi library() {
        return GroupedOpenApi.builder()
                .group("Library")
                .pathsToMatch("/api/library/**")
                .build();
    }

    @Bean
    public GroupedOpenApi objectmapper() {
        return GroupedOpenApi.builder()
                .group("Object Mapper")
                .pathsToMatch("/api/objectMapper/**")
                .build();
    }
}
