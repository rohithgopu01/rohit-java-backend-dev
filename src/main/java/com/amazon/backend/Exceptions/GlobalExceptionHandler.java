package com.amazon.backend.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazon.backend.constants.ExceptionConstants;
import com.amazon.backend.constants.ProductConstants;
import com.amazon.backend.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		
        Map<String,String> errors =new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errors.put(error.getField(),error.getDefaultMessage());
		});
		
		ApiResponse<Map<String, String>> response=new ApiResponse<Map<String,String>>(false,ExceptionConstants.API_FAILED, errors);	
		return new ResponseEntity<ApiResponse<Map<String,String>>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<ApiResponse<String>> handleException(Exception ex){
		ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(UserNotFoundException.class)
	public  ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException  ex){
		ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(InvalidPasswordException.class)
	public  ResponseEntity<ApiResponse<String>> handleInvalidPasswordException(InvalidPasswordException  ex){
		ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
}
	@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
		ApiResponse<String> response=new ApiResponse<String>(false,ProductConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage() + ex.getProductId() );
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.NOT_FOUND);
		
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleInvalidInput(IllegalArgumentException ex) {
    	ApiResponse<String> response=new ApiResponse<String>(false,ProductConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<?> handleEmptyCartException(EmptyCartException ex) {
    	ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<?> handleCartItemNotFoundException(CartItemNotFoundException ex) {
    	ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> handleAddressNotFoundException(AddressNotFoundException ex) {
    	ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CartIdInvalidException.class)
    public ResponseEntity<?> handleCartIdInvalidException(CartIdInvalidException ex) {
    	ApiResponse<String> response=new ApiResponse<String>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
		return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.BAD_REQUEST);
    }
}
