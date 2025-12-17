package com.kolosov.learnjava_jc_spring.errors.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
