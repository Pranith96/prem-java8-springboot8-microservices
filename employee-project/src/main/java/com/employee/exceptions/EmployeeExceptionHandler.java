package com.employee.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(EmployeeBusinessException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeBusinessException(EmployeeBusinessException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setDescription("Employee Data Not Found");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setExceptionCode(HttpStatus.NOT_FOUND.toString());
		exceptionResponse.setDateTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setDescription("Employee Data Validation incorrect");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setExceptionCode(HttpStatus.BAD_REQUEST.toString());
		exceptionResponse.setDateTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setDescription("Http Method incorrect. Provide valid Http method");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setExceptionCode(HttpStatus.METHOD_NOT_ALLOWED.toString());
		exceptionResponse.setDateTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionResponse);
	}
}
