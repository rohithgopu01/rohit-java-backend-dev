package com.amazon.backend.Exceptions;

public class CartIdInvalidException extends RuntimeException{
	
	private int CartId=0;
	private int UserId=0;

	public CartIdInvalidException(String message,int CartId,int UserId) {
		super(message);
		this.CartId=CartId;
		this.UserId=UserId;
	}
	public int getCartId() {
		return this.CartId;
	}
	public int getUserId() {
		return this.UserId;
	}
	
}
