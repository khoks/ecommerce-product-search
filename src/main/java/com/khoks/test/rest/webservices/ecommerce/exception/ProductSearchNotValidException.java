package com.khoks.test.rest.webservices.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductSearchNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 921353730639370187L;

	public ProductSearchNotValidException(String message) {
		super(message);
	}

}
