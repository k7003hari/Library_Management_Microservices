package com.cts.exception;

public class InvalidMembershipStatusException extends RuntimeException {
    public InvalidMembershipStatusException(String message) {
        super(message);
    }
}