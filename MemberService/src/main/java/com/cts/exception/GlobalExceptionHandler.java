package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("MEMBER_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
 
    @ExceptionHandler(InvalidMembershipStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMembershipStatusException(InvalidMembershipStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_MEMBERSHIP_STATUS", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
// 
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        StringBuilder message = new StringBuilder();
//        ex.getBindingResult().getAllErrors().forEach(error -> message.append(error.getDefaultMessage()).append("; "));
//        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_FAILED", message.toString());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
 
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred");
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
 
    // Define ErrorResponse class for consistency
    public static class ErrorResponse {
        private String errorCode;
        private String message;
 
        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
 
        public String getErrorCode() {
            return errorCode;
        }
 
        public String getMessage() {
            return message;
        }
    }
}