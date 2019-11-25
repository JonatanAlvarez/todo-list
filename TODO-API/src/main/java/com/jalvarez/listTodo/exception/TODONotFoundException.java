package com.jalvarez.listTodo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TODONotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TODONotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
	}
	
}
