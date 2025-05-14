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

	@ExceptionHandler(BookNotFoundException.class)
	public Map<String, String> handleBookNotFound(BookNotFoundException ex) {
		return Map.of("error", ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		return errors;
	}
	@ExceptionHandler(UnauthorizedAccessException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, String> handleUnauthorized(UnauthorizedAccessException ex) {
	    Map<String, String> error = new HashMap<>();
	    error.put("error", ex.getMessage());
	    return error;
	}
}