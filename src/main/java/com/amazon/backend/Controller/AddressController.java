package com.amazon.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.Entity.Address;
import com.amazon.backend.Service.AddressService;
import com.amazon.backend.constants.AddressConstants;

import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.AddressAddApiData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Address>> getAddress(@Valid  @RequestBody AddressAddApiData   addressAddApiData) {
		
		Address address = addressService.addAddress(addressAddApiData);
		ApiResponse<Address> apiResponse=new ApiResponse<Address>(true,AddressConstants.SUCCESS_API_OK,address);
 	    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		
	}
	@GetMapping("/{userId}/view")
	public ResponseEntity<ApiResponse<List<Address>>> getAddress(@PathVariable int userId){
		List<Address> addresses=addressService.getAddress(userId);
		ApiResponse<List<Address>> apiResponse=new ApiResponse<List<Address>>(true,AddressConstants.SUCCESS_API_OK,addresses);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	@DeleteMapping("/{userId}/{addressId}/delete")
	public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable int userId,@PathVariable int addressId) {
		addressService.deleteAddress(userId, addressId);
		ApiResponse<String> apiResponse=new ApiResponse<String>(true,AddressConstants.SUCCESS_API_OK,AddressConstants.SUCCESS_ADDRESS_DELETED);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
