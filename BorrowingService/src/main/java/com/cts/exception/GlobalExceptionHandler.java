package com.cts.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BorrowingTransactionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BorrowingTransactionNotFoundException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return error;
	}

	@ExceptionHandler(BorrowingNotAllowedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleNotAllowed(BorrowingNotAllowedException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
		return errors;
	}
}