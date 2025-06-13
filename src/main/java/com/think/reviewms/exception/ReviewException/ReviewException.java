package com.think.reviewms.exception.ReviewException;

import org.springframework.http.HttpStatus;

public class ReviewException {

    
    private final HttpStatus httpStatus;
    private final String message;
    private final String error;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    public ReviewException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.error = throwable != null?throwable.getLocalizedMessage() : null;
        this.httpStatus = httpStatus;
    }

}
