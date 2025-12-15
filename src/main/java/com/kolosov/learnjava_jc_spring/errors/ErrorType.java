package com.kolosov.learnjava_jc_spring.errors;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("Application error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND("Not found", HttpStatus.NOT_FOUND),
    RESOURCE_NOT_FOUND("Resource not found", HttpStatus.NOT_FOUND);

    public final String title;
    public final HttpStatus httpStatus;
    ErrorType(String title, HttpStatus httpStatus) {
        this.title = title;
        this.httpStatus = httpStatus;
    }
}
