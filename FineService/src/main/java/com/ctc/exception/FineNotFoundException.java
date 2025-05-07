package com.ctc.exception;

public class FineNotFoundException extends RuntimeException {
	public FineNotFoundException(String message) {
		super(message);
	}
}