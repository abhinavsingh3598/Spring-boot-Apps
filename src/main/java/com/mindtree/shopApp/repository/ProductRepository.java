package com.mindtree.shopApp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shopApp.model.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByproductCategory(String category);

	@Transactional
	@Query("select p from Product p where p.productName=?1")
	Product getProductByName(String productName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE product p SET p.productquantity=:productquantity where p.product_id= :productId")
	int updateQuantity(@Param("productId") int productId, @Param("productquantity") int productquantity);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from product where product_id=:productId")
	int deleteQuantity(@Param("productId") int productId);

}
