package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBookNotFound(BookNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(DuplicateBookException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleBookAlreadyExists(DuplicateBookException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGenericException(Exception ex) {
		return "An error occurred: " + ex.getMessage();
	}
}