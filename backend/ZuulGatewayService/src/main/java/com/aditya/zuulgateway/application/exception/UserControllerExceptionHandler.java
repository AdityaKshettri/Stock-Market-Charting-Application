package com.aditya.zuulgateway.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler 
{
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e)
	{
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(errorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e)
	{
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e)
	{
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
}
