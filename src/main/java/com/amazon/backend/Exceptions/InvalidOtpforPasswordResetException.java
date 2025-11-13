package com.amazon.backend.Exceptions;

public class InvalidOtpforPasswordResetException extends RuntimeException{
	
	public InvalidOtpforPasswordResetException(String message) {
		super(message);
	}

}
