package com.kolosov.learnjava_jc_spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Шаблон Spring MVC"
        )
)
public class OpenApiConfig {
}
