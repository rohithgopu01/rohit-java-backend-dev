package com.amazon.backend.Entity;

import java.time.LocalDateTime;

import com.amazon.backend.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity( name = "orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private int userId;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private Double totalAmount;
	
	private int billingAddressId;
	
	private int shippingAddressId;
	
	private LocalDateTime createdOn = LocalDateTime.now();
	
	private LocalDateTime updatedOn = LocalDateTime.now();
	
	
	
}
