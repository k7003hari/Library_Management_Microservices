package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(NotificationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotificationNotFound(NotificationNotFoundException ex) {
        return new ErrorResponse("NOTIFICATION_NOT_FOUND", ex.getMessage());
    }
 
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherErrors(Exception ex) {
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "Something went wrong");
    }
}