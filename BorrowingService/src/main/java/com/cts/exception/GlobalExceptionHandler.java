package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BorrowingNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBorrowingNotFound(BorrowingNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(BookUnavailableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBookUnavailable(BookUnavailableException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(BookAlreadyReturnedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleAlreadyReturned(BookAlreadyReturnedException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBookNotFound(BookNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(MemberNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleMemberNotFound(MemberNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(MemberInactiveException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMemberInactive(MemberInactiveException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneral(Exception ex) {
		return "Internal error: " + ex.getMessage();
	}
}