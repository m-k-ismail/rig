package com.getir.rig.domain.model.error;

import org.springframework.http.HttpStatus;

public class Error {

    private final String message;
    private HttpStatus httpStatus;

    public Error(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
