package com.ctc.exception;

public class FineAlreadyPaidException extends RuntimeException {
	public FineAlreadyPaidException(String message) {
		super(message);
	}
}