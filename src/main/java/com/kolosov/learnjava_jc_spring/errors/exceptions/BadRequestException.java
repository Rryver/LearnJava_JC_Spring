package com.kolosov.learnjava_jc_spring.errors.exceptions;

public class BadRequestException extends AppException {
    public BadRequestException(String msg) {
        super(msg);
    }
}