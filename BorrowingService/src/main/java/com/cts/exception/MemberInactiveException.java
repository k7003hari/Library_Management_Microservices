package com.cts.exception;

public class MemberInactiveException extends RuntimeException {
	public MemberInactiveException(String message) {
		super(message);
	}
}