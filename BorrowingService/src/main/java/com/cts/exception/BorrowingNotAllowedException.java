package com.cts.exception;

public class BorrowingNotAllowedException extends RuntimeException {
    public BorrowingNotAllowedException(String message) {
        super(message);
    }
}