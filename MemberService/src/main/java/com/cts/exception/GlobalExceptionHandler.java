package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleMemberNotFound(MemberNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(DuplicateMemberException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleDuplicateMember(DuplicateMemberException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleInvalidArgument(IllegalArgumentException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGenericException(Exception ex) {
		return "An error occurred: " + ex.getMessage();
	}
}