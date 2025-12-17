package com.kolosov.learnjava_jc_spring.errors.exceptions;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
