package com.amazon.backend.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.Service.CartService;
import com.amazon.backend.Service.EmailService;

import com.amazon.backend.constants.CartConstants;
import com.amazon.backend.dto.CartDto;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.AddtoCartData;
import com.amazon.backend.pojo.UpdateCartData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<String>> addToCart(@Valid  @RequestBody AddtoCartData addtoCartData){
		
		cartService.addToCart(addtoCartData);
		ApiResponse<String> apiResponse=new ApiResponse<String>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_API_MESSAGE);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	@GetMapping("/view/{userId}")
	public ResponseEntity< ApiResponse<List<CartDto>>> getCart(@Valid  @PathVariable int userId) {
	    List<CartDto> dataList=cartService.getCart(userId);
	    ApiResponse<List<CartDto>> apiResponse=new ApiResponse<List<CartDto>>(true,CartConstants.SUCCESS_API_OK,dataList);
	    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse<String>> updateCart(@Valid  @PathVariable int cartItemId,@RequestBody UpdateCartData updateCartData){
    	cartService.updateCart(cartItemId, updateCartData);
    	 ApiResponse<String> apiResponse=new ApiResponse<String>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_CART_UPDATED);
 	    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse<String>> deleteCartItem(@Valid  @PathVariable int cartItemId){
    	cartService.deleteCartItem(cartItemId);
    	ApiResponse<String> apiResponse=new ApiResponse<String>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_CART_PRODUCT_DELETED);
 	    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/send-email")
	public ResponseEntity<ApiResponse<String>> sendEmail() throws Exception {
		
		String fromEmail="rohithgopu01@gmail.com";
		String toEmail="rohithgopu05@gmail.com";
		String subject="test email";
		String mailBody="hi Rohith Gopu,Your cart item reminders for product id 25";
		
		emailService.sendPlainEmail(fromEmail, toEmail, subject, mailBody);
		
		ApiResponse<String> apiResponse=new ApiResponse<String>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_CART_EMAIL_SENT);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
			

}
}
