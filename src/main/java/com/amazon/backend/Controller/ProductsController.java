package com.amazon.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.Entity.Product;
import com.amazon.backend.Service.ProductsService;
import com.amazon.backend.constants.ProductConstants;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.ProductsAPIData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/product")
public class ProductsController {

	
	@Autowired
	private ProductsService productsService;
	
	@PostMapping("/search")
	public ResponseEntity<ApiResponse<List<Product>>> searchProducts(@RequestBody ProductsAPIData productsAPIData) {
      List<Product> products=productsService.searchProducts(productsAPIData);	
      ApiResponse<List<Product>> apiResponse=new ApiResponse<List<Product>>(true,ProductConstants.PRODUCT_SEARCH_SUCCESS, products);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<Product>>  getProductdetails(@PathVariable int productId) {
		Product product=productsService.getProductDetails(productId);
		ApiResponse<Product> apiResponse=new ApiResponse<Product>(true,ProductConstants.PRODUCT_SEARCH_SUCCESS, product);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
