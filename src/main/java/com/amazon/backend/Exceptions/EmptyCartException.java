package com.amazon.backend.Exceptions;

public class EmptyCartException extends RuntimeException{
	
	public EmptyCartException(String message) {
		super(message);
	}

}
