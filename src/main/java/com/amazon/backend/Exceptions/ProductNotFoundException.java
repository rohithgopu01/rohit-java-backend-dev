
package com.amazon.backend.Exceptions;

public class ProductNotFoundException extends RuntimeException {
	
	private int productId;
	 public ProductNotFoundException(String message) {
	        super(message);
	 }
    public ProductNotFoundException(String message,int productId) {
        super(message);
        this.productId =productId;
    }
    public int getProductId() {
		return this.productId;
	}
}