package com.kolosov.learnjava_jc_spring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
    public static final int MAX_FAILED_ATTEMPTS = 5;
}
