package com.amazon.backend.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amazon.backend.Entity.Product;
import com.amazon.backend.Exceptions.ProductNotFoundException;
import com.amazon.backend.Repository.ProductsRepository;
import com.amazon.backend.constants.ProductConstants;
import com.amazon.backend.pojo.ProductsAPIData;
@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Transactional
	public List<Product> searchProducts(ProductsAPIData productsAPIData){
		
	List<Product> products=productsRepository.searchProducts(productsAPIData.getSearchWord());
     if (products.isEmpty()) {
         throw new ProductNotFoundException(ProductConstants.EXCEPTION_PRODUCT_NOT_FOUND);
     }
         if (productsAPIData == null || productsAPIData.getSearchWord() == null || productsAPIData.getSearchWord().isBlank()) {
             throw new IllegalArgumentException(ProductConstants.EXCEPTION_PRODUCT_SEARCHWORD_NOT_NULL);
         }
	return products;
	}
	public Product getProductDetails(int productId) {
		Optional<Product> dbdataOptional=productsRepository.findById(productId);
		if (dbdataOptional.isEmpty()==true) {
			throw new ProductNotFoundException(ProductConstants.EXCEPTION_PRODUCT_NOT_FOUND);
		}
		return dbdataOptional.get();
}
}
