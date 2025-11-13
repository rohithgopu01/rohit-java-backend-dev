package com.amazon.backend.pojo;

import lombok.Data;

@Data
public class UpdateCartData {

	private Long cartId;
	private Long productId;
	private int quantity;
}
