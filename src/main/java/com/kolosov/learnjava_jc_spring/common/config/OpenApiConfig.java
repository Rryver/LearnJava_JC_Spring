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
                title = "Spring DATA - Projections"
        )
)
public class OpenApiConfig {
}
