package com.mindtree.shopApp.service;

import java.util.List;

import com.mindtree.shopApp.common.PriceProduct;
import com.mindtree.shopApp.exception.ProductNotFoundException;
import com.mindtree.shopApp.exception.QuantityShouldNotNegative;
import com.mindtree.shopApp.exception.UserIdNotFound;
import com.mindtree.shopApp.modelDto.ProductDto;

/**
 * @author M1056078
 *
 */
public interface CartService {

	String addProductToCart(int userId, ProductDto product) throws UserIdNotFound;

	PriceProduct getUserTotalAmount(int userId) throws UserIdNotFound;

	String updateTheQuantity(int userId, int quantity, int productId) throws UserIdNotFound, QuantityShouldNotNegative, ProductNotFoundException;

	String deleteProduct(List<Integer> productIds,int userId) throws UserIdNotFound, ProductNotFoundException;

	ProductDto getProduct(String productName)throws ProductNotFoundException;

	ProductDto getProductById(int productId)throws ProductNotFoundException;

	List<ProductDto> getProductsByCategory(String productCategory) throws ProductNotFoundException;
	
}
