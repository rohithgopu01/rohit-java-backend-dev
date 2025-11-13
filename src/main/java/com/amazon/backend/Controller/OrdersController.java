package com.amazon.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.Entity.Order;
import com.amazon.backend.Service.OrderService;
import com.amazon.backend.constants.OrderConstants;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.OrderApiData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Order>>  createOrder(@RequestBody OrderApiData orderApiData) {
		
		Order order=orderService.createOrder(orderApiData);
		ApiResponse<Order> apiResponse=new ApiResponse<Order>(true,OrderConstants.SUCCESS_API_OK,order);
 	    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		
	}
	
}
