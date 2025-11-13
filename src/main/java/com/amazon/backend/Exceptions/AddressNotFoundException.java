package com.amazon.backend.Exceptions;

public class AddressNotFoundException extends RuntimeException{

	private int userId;
	private int addressId;
	
	public AddressNotFoundException(String message,int userId,int addressId) {
		super(message);
		this.userId=userId;
		this.addressId=addressId;;
	}
	public int getUserId(){
		return this.userId;
	}
	public int getAddressId(){
		return this.addressId;
	}
}
