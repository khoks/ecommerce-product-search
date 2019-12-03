package com.khoks.test.rest.webservices.ecommerce.exception;

import java.util.Date;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		ExceptionResponseTemplate exceptionResponse = new ExceptionResponseTemplate(new Date(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<Object> handleProductNotFoundException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponseTemplate exceptionResponse = new ExceptionResponseTemplate(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ProductSearchNotValidException.class)
	public final ResponseEntity<Object> handleProductSearchNotValidException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponseTemplate exceptionResponse = new ExceptionResponseTemplate(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponseTemplate exceptionResponse = new ExceptionResponseTemplate(new Date(), "Request validation failed.",
				ex.getBindingResult().getAllErrors().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponseTemplate exceptionResponse = new ExceptionResponseTemplate(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
