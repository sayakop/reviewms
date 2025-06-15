package com.think.reviewms.exception.ReviewException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundReviewException extends RuntimeException{

    public NotFoundReviewException(String message)
    {
        super(message);
    }
    public NotFoundReviewException(String message, Throwable cause)
    {
        super(message,cause);
    }


}
