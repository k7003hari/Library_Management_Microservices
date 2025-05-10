package com.ctc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FineNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleFineNotFoundException(FineNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(FineAlreadyPaidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleFineAlreadyPaidException(FineAlreadyPaidException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGenericException(Exception ex) {
		return "An unexpected error occurred: " + ex.getMessage();
	}
}