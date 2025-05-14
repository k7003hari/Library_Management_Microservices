package com.ctc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FineNotFoundException.class)
	public ErrorResponse handleFineNotFoundException(FineNotFoundException ex, HttpServletRequest request) {
		return new ErrorResponse("FINE_NOT_FOUND", ex.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ErrorResponse handleUnauthorizedAccessException(UnauthorizedAccessException ex, HttpServletRequest request) {
		return new ErrorResponse("UNAUTHORIZED_ACCESS", ex.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleGenericException(Exception ex, HttpServletRequest request) {
		return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred.", request.getRequestURI());
	}
}