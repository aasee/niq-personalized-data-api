package com.niq.personalized_data_api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.niq.personalized_data_api.models.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p FROM Product p WHERE p.shopper.shopperId = :shopperId"
			+ " AND (:category IS NULL OR p.category = :category)"
			+ " AND (:brand IS NULL OR p.brand = :brand)"
			+ " ORDER BY p.productId")
	List<Product> findProductsByShopperIdAndCategoryAndBrand(@Param("shopperId") String shopperId,
			@Param("category") String category, @Param("brand") String brand, Pageable pageable);

}