package com.amazon.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amazon.backend.Entity.Product;
@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

	@Procedure(procedureName = "sp_search_products_data")
	List<Product> searchProducts(@Param("inp_search_word") String searchWord);
}
