package com.cts.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(BorrowingNotFoundException.class)
    public ResponseEntity<String> handleBorrowingNotFound(BorrowingNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(500).body("An unexpected error occurred: " + ex.getMessage());
    }
}