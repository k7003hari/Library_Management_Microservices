package com.cts.exception;

public class BorrowingTransactionNotFoundException extends RuntimeException {
    public BorrowingTransactionNotFoundException(String message) {
        super(message);
    }
}