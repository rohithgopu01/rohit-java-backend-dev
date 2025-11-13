package com.amazon.backend.constants;

public class CartConstants {

	public static final String ERROR_API_DATA_USER_ID="User id should be greater than zero";
	public static final String ERROR_API_DATA_PRODUCT_ID="Product id should be greater than zero";
	public static final String ERROR_API_DATA_QUANTITY="Quantity should be greater than zero";
	
	
	public static final String SUCCESS_API_OK="OK";
	public static final String SUCCESS_API_MESSAGE="Product added to cart successfully.";
	public static final String SUCCESS_CART_UPDATED="Product Quantity updated successfully.";
	public static final String SUCCESS_CART_PRODUCT_DELETED="Product Deleted successfully";
	public static final String SUCCESS_CART_EMAIL_SENT="Email sent successful";
	
	
	
	public static final String EXCEPTION_EMPTY_CART="No Products in the cart";
	public static final String EXCEPTION_CART_ITEM_NOT_FOUND="NO cart item found with the received id";
	
}
