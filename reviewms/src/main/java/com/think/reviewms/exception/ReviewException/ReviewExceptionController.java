package com.think.reviewms.exception.ReviewException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ReviewExceptionController{
    @ExceptionHandler(value = {NotFoundReviewException.class})
    public ResponseEntity<Object> handleNotFoundReviewException(NotFoundReviewException notFoundReviewException)
    {
        ReviewException reviewException = new ReviewException(
                 notFoundReviewException.getMessage(),
                 notFoundReviewException.getCause(),
                 HttpStatus.NOT_FOUND
         );
         return new ResponseEntity<>(reviewException, HttpStatus.NOT_FOUND);
     }
    }
