package com.deltacode.kcb.exception;

import org.springframework.http.HttpStatus;

public class FieldPortalApiException extends  RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public FieldPortalApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
